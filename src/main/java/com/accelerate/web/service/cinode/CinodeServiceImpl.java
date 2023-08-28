package com.accelerate.web.service.cinode;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.jpa.Assignment;
import com.accelerate.web.jpa.AssignmentRepository;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.accelerate.web.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinodeServiceImpl implements CinodeService {

    @Autowired
    AssignmentRepository repository;
    @Autowired
    WebhookJsonMapper mapper;

    Logger logger = LoggerFactory.getLogger(CinodeServiceImpl.class);

    public String handleRequestFromCinode(String requestBody) throws JsonProcessingException {

        CinodeMarketRequestDto cinodeMarketRequestDtoDto = mapper.mapWebhookJsonToDto(requestBody);
        Assignment assignmentEntity = new Assignment();
        assignmentEntity.setAction(cinodeMarketRequestDtoDto.getMeta().getAction());

        if ((cinodeMarketRequestDtoDto.getMeta().getAction().equals(StringUtils.CREATED) ||
                cinodeMarketRequestDtoDto.getMeta().getAction().equals(StringUtils.UPDATED)) &&
                cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName().equals(StringUtils.FOREFRONT)) {
            mapper.mapDtoToEntity(cinodeMarketRequestDtoDto, assignmentEntity);
            saveAssignmentToDb(assignmentEntity);
            logger.info("cinodeMarketRequestDtoDto.getMeta().getAction(): " + cinodeMarketRequestDtoDto.getMeta().getAction() + ", cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName(): " + cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName());
            return cinodeMarketRequestDtoDto.getMeta().getAction();
        } else {
            logger.info("Received webhook from other company: " + cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName() + ", Action: " + cinodeMarketRequestDtoDto.getMeta().getAction());
            return "Received webhook from other company: " + cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName();
        }
    }

    private void saveAssignmentToDb(Assignment assignmentEntity) {
        if (assignmentEntity.getAction().equals(StringUtils.CREATED) && assignmentEntity.getAnnouncerCompanyName().equals(StringUtils.FOREFRONT)) {
            Assignment createdEntity = repository.save(assignmentEntity);
            logger.info("Created with: " + createdEntity);
        } else if (assignmentEntity.getAction().equals(StringUtils.UPDATED) && assignmentEntity.getAnnouncerCompanyName().equals(StringUtils.FOREFRONT)) {
            Assignment createdEntity = repository.save(assignmentEntity);
            logger.info("Updated: " + createdEntity);
        }
    }
}
