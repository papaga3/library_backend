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

import demo.group1.library.dto.request.PublisherRequest;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.dto.response.PublisherResponse;
import demo.group1.library.service.PublisherServiceInterface;
import demo.group1.library.utils.AppConstants;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class PublisherController {
	private final PublisherServiceInterface publisherService;

	@GetMapping("/publishers")
	public ResponseEntity<PaginatedResponse<PublisherResponse>> findPublishers(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAUT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(name = "publisherPartialName", defaultValue = "", required = false) String partialName) {
		PaginatedResponse<PublisherResponse> publisherResponse = publisherService.findPublishers(pageNo, pageSize,
				sortBy, sortDir, partialName);
		return new ResponseEntity<>(publisherResponse, HttpStatus.OK);
	}

	@GetMapping("/publishers/{id}")
	public ResponseEntity<PublisherResponse> findPublisherById(@PathVariable Long id) {
		try {
			var publisherResponse = publisherService.findPublisherById(id);
			return new ResponseEntity<>(publisherResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/publishers")
	public ResponseEntity<String> addPublisher(@Valid @RequestBody PublisherRequest publisherDTO) {
		publisherService.addPublisher(publisherDTO);
		return new ResponseEntity<>("publiser added", HttpStatus.OK);
	}

	@PutMapping("/publishers/{id}")
	public ResponseEntity<String> updatePublisherById(@PathVariable Long id,
			@Valid @RequestBody PublisherRequest publisherDTO) {
		try {
			publisherService.updatePublisher(id, publisherDTO);
			return new ResponseEntity<>("publisher updated", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/publishers/{id}")
	public ResponseEntity<String> deletePublisherById(@PathVariable Long id) {
		try {
			publisherService.deletePublisher(id);
			return new ResponseEntity<>("publisher deleted", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
