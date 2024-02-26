package demo.group1.library.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String bio;
	
	// books
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;
}
