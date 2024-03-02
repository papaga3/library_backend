package demo.group1.library.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.group1.library.converter.Converter;
import demo.group1.library.dto.request.BookRequest;
import demo.group1.library.dto.response.AuthorResponse;
import demo.group1.library.dto.response.BookResponse;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.entities.Author;
import demo.group1.library.entities.Book;
import demo.group1.library.repository.AuthorRepository;
import demo.group1.library.repository.BookRepository;
import demo.group1.library.repository.CategoryRepository;
import demo.group1.library.repository.PublisherRepository;
import demo.group1.library.service.BookServiceInterface;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceInterface {
	private final AuthorRepository authorRepo;
	private final CategoryRepository categoryRepo;
	private final PublisherRepository publisherRepo;
	private final BookRepository bookRepo;
	
	private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

	public PaginatedResponse<BookResponse> findBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Book> bookPage = bookRepo.findAll(pageable);
		
		List<Book> bookList = bookPage.getContent();
		List<BookResponse> content = Converter.toList(bookList, BookResponse.class);
		
		PaginatedResponse<BookResponse> response = new PaginatedResponse<BookResponse>();
		response.setContent(content);
        response.setPageNo(bookPage.getNumber());
        response.setPageSize(bookPage.getSize());
        response.setTotalElements(bookPage.getTotalElements());
        response.setTotalPages(bookPage.getTotalPages());
        response.setLast(bookPage.isLast());
        
		return response;
	}

	public BookResponse findBookById(Long id) throws Exception {
		var book = bookRepo.findById(id);
		if(!book.isPresent()) {
			String message = "Cannot find book by id: " + id;
			logger.error(message);
			
			// TODO: integrate with new exception type
			throw new Exception(message);
		}
		
		return Converter.toModel(book, BookResponse.class);
	}

	public void addBook(BookRequest bookDTO) throws Exception {
		var author = authorRepo.findById(bookDTO.getAuthorId());
		if(author.isEmpty()) {
			String message = "Invalid author ID: " + bookDTO.getAuthorId();
			logger.error(message);
			
			// TODO: integrate with new exception type
			throw new Exception(message);
		}
		
		var publisher = publisherRepo.findById(bookDTO.getPublisherId());
		
		if(publisher.isEmpty()) {
			String message = "Invalid publisher ID: " + bookDTO.getAuthorId();
			logger.error(message);
			
			// TODO: integrate with new exception type
			throw new Exception(message);
		}
		
		
		
	}

	public void updateBook(Long id, BookRequest authorDTO) throws Exception {

	}

	public void deleteBook(Long id) {

	}
}
