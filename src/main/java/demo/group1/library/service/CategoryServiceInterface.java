package demo.group1.library.service;

import org.springframework.stereotype.Service;

import demo.group1.library.dto.request.CategoryRequest;
import demo.group1.library.dto.response.CategoryResponse;
import demo.group1.library.dto.response.PaginatedResponse;

@Service
public interface CategoryServiceInterface {
	public PaginatedResponse<CategoryResponse> findCategories(
			int pageNo, 
			int pageSize,
			String sortBy,
			String sortDir,
			String partialName
	);
	
	public CategoryResponse findCategoryById(Long id) throws Exception;
	
	public void addCategory(CategoryRequest categoryDTO);
	
	public void updateCategory(Long id, CategoryRequest categoryDTO) throws Exception;
	
	public void deleteCategory(Long id);
}
