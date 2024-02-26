package demo.group1.library.service;

import org.springframework.stereotype.Service;

import demo.group1.library.dto.request.PublisherRequest;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.dto.response.PublisherResponse;


@Service
public interface PublisherServiceInterface {
	// Will return all publishers if partialName is an empty string
		public PaginatedResponse<PublisherResponse> findPublishers(
				int pageNo, 
				int pageSize,
				String sortBy,
				String sortDir,
				String partialName
		);
		
		public PublisherResponse findPublisherById(Long id) throws Exception;
		
		public void addPublisher(PublisherRequest publisherDTO);
		
		public void updatePublisher(Long id, PublisherRequest publisherDTO) throws Exception;
		
		public void deletePublisher(Long id);
}
