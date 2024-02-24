package demo.group1.library.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class author {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String first_name;
	
	private String last_name;
	
	private String bio;
	
	// books
	@OneToMany(mappedBy = "author")
	private List<book> books;
}
