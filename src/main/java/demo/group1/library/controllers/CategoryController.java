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

import demo.group1.library.dto.request.CategoryRequest;
import demo.group1.library.dto.response.CategoryResponse;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.service.impl.CategoryServiceImpl;
import demo.group1.library.utils.AppConstants;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/library")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryServiceImpl categoryService;

	@GetMapping("/categories")
	public ResponseEntity<PaginatedResponse<CategoryResponse>> findCategories(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAUT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(name = "catPartialName", defaultValue = "", required = false) String partialName) {
		PaginatedResponse<CategoryResponse> response = categoryService.findCategories(pageNo, pageSize, sortBy, sortDir,
				partialName);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
		try {
			CategoryResponse response = categoryService.findCategoryById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categories")
	public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryRequest categoryDTO) {
		categoryService.addCategory(categoryDTO);
		return new ResponseEntity<String>("Category added", HttpStatus.OK);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryRequest categoryDTO) {
		try {
			categoryService.updateCategory(id, categoryDTO);
			return new ResponseEntity<>("category updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>("category deleted", HttpStatus.OK);
	}
}
