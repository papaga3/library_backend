package demo.group1.library.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.group1.library.converter.Converter;
import demo.group1.library.dto.request.PublisherRequest;
import demo.group1.library.dto.response.PaginatedResponse;
import demo.group1.library.dto.response.PublisherResponse;
import demo.group1.library.entities.Publisher;
import demo.group1.library.repository.PublisherRepository;
import demo.group1.library.service.PublisherServiceInterface;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherServiceInterface {

	private final PublisherRepository publisherRepo;
	private static final Logger logger = LogManager.getLogger(PublisherServiceImpl.class);

	// Will return all publishers if partialName is an empty string
	public PaginatedResponse<PublisherResponse> findPublishers(int pageNo, int pageSize, String sortBy, String sortDir,
			String partialName) {
		// check sort direction
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Publisher> publisherPage;

		if (partialName.isEmpty() || partialName.equals("")) {
			publisherPage = publisherRepo.findAll(pageable);
		} else {
			publisherPage = publisherRepo.findByPartialName(pageable, partialName);
		}

		// get content from page object
		List<Publisher> publisherList = publisherPage.getContent();
		List<PublisherResponse> content = Converter.toList(publisherList, PublisherResponse.class);

		PaginatedResponse<PublisherResponse> publisherResponse = new PaginatedResponse<>();
		publisherResponse.setContent(content);
		publisherResponse.setPageNo(publisherPage.getNumber());
		publisherResponse.setPageSize(publisherPage.getSize());
		publisherResponse.setTotalElements(publisherPage.getTotalElements());
		publisherResponse.setTotalPages(publisherPage.getTotalPages());
		publisherResponse.setLast(publisherPage.isLast());

		return publisherResponse;
	}

	public PublisherResponse findPublisherById(Long id) throws Exception {
		var publisher = publisherRepo.findById(id);
		if(publisher.isEmpty()) {
			String message = "Cannot find publisher by id: " + id;
			logger.error(message);
			
			// TODO: integrate with new exception types
			throw new Exception(message);
		}
		
		return Converter.toModel(publisher, PublisherResponse.class);
	}

	public void addPublisher(PublisherRequest publisherDTO) {
		publisherRepo.save(Converter.toModel(publisherDTO, Publisher.class));
	}

	public void updatePublisher(Long id, PublisherRequest publisherDTO) throws Exception {
		var publisher = publisherRepo.findById(id);
		if(publisher.isEmpty()) {
			String message = "Cannot find publisher by id: " + id;
			logger.error(message);
			
			// TODO: integrate with new exception types
			throw new Exception(message);
		}
		
		var newPublisher = publisher.get();
		newPublisher.setName(publisherDTO.getName());
		newPublisher.setContactInfo(publisherDTO.getContactInfo());
		
		publisherRepo.save(newPublisher);
	}

	public void deletePublisher(Long id) {
		publisherRepo.deleteById(id);
	}
}
