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
import demo.group1.library.dto.request.CategoryRequest;
import demo.group1.library.dto.response.CategoryResponse;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.entities.Category;
import demo.group1.library.repository.CategoryRepository;
import demo.group1.library.service.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInterface {

	private final CategoryRepository categoryRepo;
	private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

	public PaginatedResponse<CategoryResponse> findCategories(int pageNo, int pageSize, String sortBy, String sortDir,
			String partialName) {
		// check sort direction
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Category> categoryPage;

		if (partialName.isEmpty() || partialName.equals("")) {
			categoryPage = categoryRepo.findAll(pageable);
		} else {
			categoryPage = categoryRepo.findCategoriesByPartialName(pageable, partialName);
		}

		List<Category> categoryList = categoryPage.getContent();
		List<CategoryResponse> content = Converter.toList(categoryList, CategoryResponse.class);
		PaginatedResponse<CategoryResponse> response = new PaginatedResponse<>();
		response.setContent(content);

		response.setPageNo(categoryPage.getNumber());
		response.setPageSize(categoryPage.getSize());
		response.setTotalElements(categoryPage.getTotalElements());
		response.setTotalPages(categoryPage.getTotalPages());
		response.setLast(categoryPage.isLast());
		return response;
	}

	public CategoryResponse findCategoryById(Long id) throws Exception {
		var category = categoryRepo.findById(id);

		if (category.isEmpty()) {
			String message = "Cannot find category id: " + id;
			logger.error(message);
			throw new Exception(message);
		}

		return Converter.toModel(category, CategoryResponse.class);
	}

	public void addCategory(CategoryRequest categoryDTO) {
		categoryRepo.save(Converter.toModel(categoryDTO, Category.class));
	}

	public void updateCategory(Long id, CategoryRequest categoryDTO) throws Exception {
		var category = categoryRepo.findById(id);

		if (category.isEmpty()) {
			String message = "Cannot find category id: " + id;
			logger.error(message);
			throw new Exception(message);
		}
		
		Category newCategory = category.get();
		newCategory.setName(categoryDTO.getName());
		
		categoryRepo.save(newCategory);
	}

	public void deleteCategory(Long id) {

	}
}
