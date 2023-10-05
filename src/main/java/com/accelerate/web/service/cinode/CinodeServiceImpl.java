package com.accelerate.web.service.cinode;

import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.jpa.Assignment;
import com.accelerate.web.jpa.AssignmentRepository;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.accelerate.web.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CinodeServiceImpl implements CinodeService {

    @Autowired
    AssignmentRepository repository;
    @Autowired
    WebhookJsonMapper mapper;

    Logger logger = LoggerFactory.getLogger(CinodeServiceImpl.class);

    public String handleRequestFromCinode(String requestBody) throws JsonProcessingException {

        CinodeMarketRequestDto cinodeMarketRequest = mapper.mapWebhookJsonToCinodeMarketRequest(requestBody);
        Assignment assignmentEntity = new Assignment();
        assignmentEntity.setAction(cinodeMarketRequest.getAction());

        String action = cinodeMarketRequest.getAction();

        if (cinodeMarketRequest.getAnnouncerCompanyName() != (StringUtils.FOREFRONT)) {
            logger.info(StringUtils.RECEIVED_WEBHOOK_FROM_OTHER_COMPANY + cinodeMarketRequest.getAnnouncerCompanyName() + ", Action: " + cinodeMarketRequest.getAction());
            return StringUtils.RECEIVED_WEBHOOK_FROM_OTHER_COMPANY + cinodeMarketRequest.getAnnouncerCompanyName();
        }

        switch(action) {
            case StringUtils.CREATED, StringUtils.UPDATED -> {
                mapper.mapDtoToEntity(cinodeMarketRequest, assignmentEntity);
                saveAssignmentToDb(assignmentEntity);
                logger.info("Action: " + cinodeMarketRequest.getAction() + ", set by company: " + cinodeMarketRequest.getAnnouncerCompanyName());
                return cinodeMarketRequest.getAction();
            }
            case StringUtils.DELETED -> {
                mapper.mapDtoToEntity(cinodeMarketRequest, assignmentEntity);
                deleteAssignmentFromDb(assignmentEntity);
                return cinodeMarketRequest.getAction();
            }
            default -> {
                return "Something went wrong";
            }
        }
    }

    private void saveAssignmentToDb(Assignment assignmentEntity) {
            Assignment createdEntity = repository.save(assignmentEntity);
            logger.info(createdEntity.getAction() + " in db: " + createdEntity);
    }

    private void deleteAssignmentFromDb(Assignment assignmentEntity) {
            Long id = Long.valueOf(assignmentEntity.getCinodeId());
            Optional<Assignment> foundEntity = repository.findById(id);
            if (foundEntity.isPresent()) {
                repository.delete(assignmentEntity);
                logger.info("Assignment deleted with id: " + assignmentEntity.getCinodeId());
            } else {
                logger.info("Assignment not present with id: " + assignmentEntity.getCinodeId());
            }
    }
}
