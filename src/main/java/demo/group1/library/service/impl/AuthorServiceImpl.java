package demo.group1.library.service.impl;

import org.apache.log4j.Logger;

import java.util.List;

import org.apache.log4j.LogManager;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import demo.group1.library.converter.Converter;
import demo.group1.library.dto.request.AuthorRequest;
import demo.group1.library.dto.response.AuthorResponse;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.entities.Author;
import demo.group1.library.repository.AuthorRepository;
import demo.group1.library.service.AuthorServiceInterface;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorServiceInterface {
	private final AuthorRepository authorRepo;
	
	private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);
	
	@Override
	public PaginatedResponse<AuthorResponse> findAuthors(
			int pageNo, 
			int pageSize,
			String sortBy,
			String sortDir,
			String partialName
	) {
		// check sort direction
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Author> authorPage;
        
        if(partialName.isEmpty() || partialName.equals("")) {
            authorPage = authorRepo.findAll(pageable);
        } else {
        	authorPage = authorRepo.findByPartialName(pageable, partialName);
        }

        // get content from page object
        List<Author> authorList = authorPage.getContent();
        List<AuthorResponse> content = Converter.toList(authorList, AuthorResponse.class);

        PaginatedResponse<AuthorResponse> authorResponse = new PaginatedResponse<>();
        authorResponse.setContent(content);
        authorResponse.setPageNo(authorPage.getNumber());
        authorResponse.setPageSize(authorPage.getSize());
        authorResponse.setTotalElements(authorPage.getTotalElements());
        authorResponse.setTotalPages(authorPage.getTotalPages());
        authorResponse.setLast(authorPage.isLast());

        return authorResponse;
	}
	
	@Override
	public AuthorResponse findAuthorById(Long id) throws Exception {
		var author = authorRepo.findById(id);
		
		if(!author.isPresent()) {
			String message = "Cannot find author by id: " + id;
			logger.error(message);
			
			// TODO: integrate with new exception type
			throw new Exception(message);
		}
		
		return Converter.toModel(author, AuthorResponse.class);
	}
	
	@Override
	public void addAuthor(AuthorRequest authorDTO) {
		authorRepo.save(Converter.toModel(authorDTO, Author.class));
	}
	
	@Override
	public void updateAuthor(Long id, AuthorRequest authorDTO) throws Exception {
		var existingAuthor = authorRepo.findById(id);
		
		if(!existingAuthor.isPresent()) {
			String message = "Cannot find author by id: " + id;
			logger.error(message);
			
			// TODO: integrate with new exception type
			throw new Exception(message);
		}
		
		Author newAuthor = existingAuthor.get();
		newAuthor.setFirstName(authorDTO.getFirstName());
		newAuthor.setLastName(authorDTO.getLastName());
		
		authorRepo.save(newAuthor);
	}
	
	@Override
	public void deleteAuthor(Long id) {
		authorRepo.deleteById(id);
	}
	
}
