package demo.group1.library.dto.response;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
	private Long Id;
	
	@NotEmpty
	private String ISBN;
	
	@NotEmpty
	private String title;
	
	private AuthorResponse author;
	
	@NotEmpty
	private String language;
	
	@NotEmpty
	private String description;
	
	List<CategoryResponse> categories;
	
	private PublisherResponse publisher;
	
	private int quantity;
}
