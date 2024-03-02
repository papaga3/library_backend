package demo.group1.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.group1.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
