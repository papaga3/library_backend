package demo.group1.library.service;

import org.springframework.stereotype.Service;

import demo.group1.library.dto.request.BookRequest;
import demo.group1.library.dto.response.BookResponse;
import demo.group1.library.dto.response.PaginatedResponse;

@Service
public interface BookServiceInterface {
	public PaginatedResponse<BookResponse> findBooks(
			int pageNo, 
			int pageSize,
			String sortBy,
			String sortDir
	);
	
	public BookResponse findBookById(Long id) throws Exception;
	
	public void addBook(BookRequest bookDTO) throws Exception;
	
	public void updateBook(Long id, BookRequest authorDTO) throws Exception;
	
	public void deleteBook(Long id);
}
