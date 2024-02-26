package demo.group1.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.group1.library.entities.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
	@Query("SELECT p FROM Publisher p WHERE LOCATE(:partialName, p.name)<>0")
	Page<Publisher> findByPartialName(Pageable pageable, String partialName);
}
