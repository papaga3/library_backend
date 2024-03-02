package demo.group1.library.dto.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BookRequest {
	private String ISBN;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private Long authorId;
	
	@NotEmpty
	private String language;
	
	@NotEmpty
	private String description;
	
	List<Long> categoryIdList;
	
	private Long publisherId;
	
	private int quantity;
}
