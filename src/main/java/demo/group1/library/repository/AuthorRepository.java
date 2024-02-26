package demo.group1.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.group1.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
