package demo.group1.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.group1.library.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("SELECT c FROM Category c WHERE LOCATE(:partialName, c.name)<>0")
	Page<Category> findCategoriesByPartialName(Pageable pageable, String partialName);
}
