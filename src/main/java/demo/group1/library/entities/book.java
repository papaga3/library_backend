package demo.group1.library.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ISBN;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private author author;
	
	private String language;
	
	private String description;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
		name = "book_category",
		joinColumns = @JoinColumn(name="book_id"),
		inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id")
	)
	private List<category> categories;
	
	@ManyToOne
	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	private publisher publisher;
	
	private int quantity;
}
