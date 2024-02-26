package demo.group1.library.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.group1.library.dto.request.AuthorRequest;
import demo.group1.library.dto.response.AuthorResponse;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.service.AuthorServiceInterface;
import demo.group1.library.utils.AppConstants;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class AuthorController {
	private final AuthorServiceInterface authorService;
	
	@GetMapping("/authors")
	public ResponseEntity<PaginatedResponse<AuthorResponse>> findAllAuthors(
			   @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAUT_SORT_BY, required = false) String sortBy,
	            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	) {
		 PaginatedResponse<AuthorResponse> authorResponse = authorService.findAllAuthors(pageNo, pageSize, sortBy, sortDir);
	     return new ResponseEntity<>(authorResponse, HttpStatus.OK);
	}
	
	@GetMapping("/authors/{id}")
	public ResponseEntity<AuthorResponse> findAuthorById(@PathVariable Long id) {
		try {
			var author = authorService.findAuthorById(id);
			return new ResponseEntity<>(author, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/authors")
	public ResponseEntity<String> addNewAuthor(@Valid @RequestBody AuthorRequest authorDTO) {
		authorService.addAuthor(authorDTO);
		return new ResponseEntity<>("Author added", HttpStatus.OK);
	}
	
	@PutMapping("/authors/{id}")
	public ResponseEntity<String> updateAuthorById(
			@PathVariable Long id, 
			@RequestBody AuthorRequest authorDTO
	) 
	{
		try {
			authorService.updateAuthor(id, authorDTO);
			return new ResponseEntity<>("Author added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/authors/{id}")
	public ResponseEntity<String> deleteAuthorById(
			@PathVariable Long id
	) 
	{
		try {
			authorService.deleteAuthor(id);
			return new ResponseEntity<>("Author added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
