package demo.group1.library.service;

import org.springframework.stereotype.Service;

import demo.group1.library.dto.request.AuthorRequest;
import demo.group1.library.dto.response.AuthorResponse;
import demo.group1.library.dto.response.PaginatedResponse;

@Service
public interface AuthorServiceInterface {
	public PaginatedResponse<AuthorResponse> findAllAuthors(
			int pageNo, 
			int pageSize,
			String sortBy,
			String sortDir
	);
	
	public AuthorResponse findAuthorById(Long id) throws Exception;
	
	public void addAuthor(AuthorRequest authorDTO);
	
	public void updateAuthor(Long id, AuthorRequest authorDTO) throws Exception;
	
	public void deleteAuthor(Long id);
	
}