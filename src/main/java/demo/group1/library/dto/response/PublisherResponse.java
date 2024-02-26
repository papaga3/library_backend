package demo.group1.library.dto.response;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublisherResponse {
	private Long id;
	
	@NotEmpty
	private String contactInfo;
	
	@NotEmpty
	private String name;
}
