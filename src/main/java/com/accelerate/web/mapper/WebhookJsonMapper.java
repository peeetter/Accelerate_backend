package com.accelerate.web.mapper;

import com.accelerate.web.dto.CinodeMarketRequest;
import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class WebhookJsonMapper {

public CinodeMarketRequestDto mapWebhookJsonToCinodeMarketRequest(String jsonObject) throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();;
    CinodeMarketRequest assignments = objectMapper.readValue(jsonObject, CinodeMarketRequest.class);
    if (assignments.getPayload().getAnnouncerCompanyName().contains("Forefront")) {
        assignments.getPayload().setAnnouncerCompanyName(("Forefront"));
    }

    CinodeMarketRequestDto cinodeMarketRequestDto = new CinodeMarketRequestDto();
    cinodeMarketRequestDto.setCinodeId(assignments.getMeta().getCinodeId());
    cinodeMarketRequestDto.setAction(assignments.getMeta().getAction());
    cinodeMarketRequestDto.setTitle(assignments.getPayload().getTitle());
    cinodeMarketRequestDto.setDescription(assignments.getPayload().getDescription());
    cinodeMarketRequestDto.setAllowRemote(assignments.getPayload().isAllowRemote());
    cinodeMarketRequestDto.setStartDate(assignments.getPayload().getStartDate());
    cinodeMarketRequestDto.setEndDate(assignments.getPayload().getEndDate());
    cinodeMarketRequestDto.setAnnouncedDate(assignments.getPayload().getAnnouncedDate());
    cinodeMarketRequestDto.setAnnouncerCompanyName(assignments.getPayload().getAnnouncerCompanyName());
    fixLocationForDto(assignments,cinodeMarketRequestDto);

    return cinodeMarketRequestDto;
}
public void mapDtoToEntity(CinodeMarketRequestDto cinodeMarketRequestDto, com.accelerate.web.jpa.Assignment assignmentEntity) {
        assignmentEntity.setCinodeId(cinodeMarketRequestDto.getCinodeId());
        assignmentEntity.setAction(cinodeMarketRequestDto.getAction());
        assignmentEntity.setDeadlineDate(cinodeMarketRequestDto.getDeadlineDate());
        assignmentEntity.setTitle(cinodeMarketRequestDto.getTitle());
        assignmentEntity.setDescription(cinodeMarketRequestDto.getDescription());
        assignmentEntity.setAllowRemote(cinodeMarketRequestDto.isAllowRemote());
        assignmentEntity.setStartDate(cinodeMarketRequestDto.getStartDate());
        assignmentEntity.setEndDate(cinodeMarketRequestDto.getEndDate());
        assignmentEntity.setAnnouncerCompanyName(cinodeMarketRequestDto.getAnnouncerCompanyName());
        assignmentEntity.setAnnouncedDate(cinodeMarketRequestDto.getAnnouncedDate());
        assignmentEntity.setCity(cinodeMarketRequestDto.getCity());
        assignmentEntity.setDisplayName(cinodeMarketRequestDto.getDisplayName());
}

public void fixLocationForDto(CinodeMarketRequest cinodeMarketRequest, CinodeMarketRequestDto cinodeMarketRequestDto) {
    if (cinodeMarketRequest.getPayload().getLocation() == null) {
        cinodeMarketRequestDto.setCity(StringUtils.NOT_APPLICABLE);
        cinodeMarketRequestDto.setDisplayName(StringUtils.NOT_APPLICABLE);
    } else {
        if (cinodeMarketRequest.getPayload().getLocation().getDisplayName() != null && cinodeMarketRequest.getPayload().getLocation().getCity() != null) {
            cinodeMarketRequestDto.setDisplayName(cinodeMarketRequest.getPayload().getLocation().getDisplayName());
            cinodeMarketRequestDto.setCity(cinodeMarketRequest.getPayload().getLocation().getCity());
        }
        if (cinodeMarketRequest.getPayload().getLocation().getDisplayName() != null && cinodeMarketRequest.getPayload().getLocation().getCity() == null) {
            cinodeMarketRequestDto.setDisplayName(cinodeMarketRequest.getPayload().getLocation().getDisplayName());
            cinodeMarketRequestDto.setCity(StringUtils.NOT_APPLICABLE);
        }
        if (cinodeMarketRequest.getPayload().getLocation().getCity() != null && cinodeMarketRequest.getPayload().getLocation().getDisplayName() == null) {
            cinodeMarketRequestDto.setCity(cinodeMarketRequest.getPayload().getLocation().getCity());
            cinodeMarketRequestDto.setDisplayName(StringUtils.NOT_APPLICABLE);
        }
    }
}

}
