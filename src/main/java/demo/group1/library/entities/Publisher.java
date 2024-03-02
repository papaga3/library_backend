package demo.group1.library.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publishers")
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactInfo;

    private String name;
    
    // books
 	@OneToMany(mappedBy = "publisher")
 	private List<Book> books;
 	
 	public void addBook(Book b) {
		 books.add(b);
	}
	
	public boolean removeBook(Book b) {
		return books.remove(b);
	}
}
