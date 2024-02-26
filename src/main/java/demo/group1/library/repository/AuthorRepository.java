package demo.group1.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.group1.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	@Query("SELECT a FROM Author a WHERE "
			+ "LOCATE(:partialName, a.firstName)<>0 "
			+ "OR LOCATE(:partialName, a.lastName)<>0")
	Page<Author> findByPartialName(Pageable pageable, String partialName);
}
