package demo.group1.library.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublisherRequest {
	@NotEmpty
	private String contactInfo;
	
	@NotEmpty
	private String name;
}
