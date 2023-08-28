package com.accelerate.web.mapper;

import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class WebhookJsonMapper {

public CinodeMarketRequestDto mapWebhookJsonToDto(String jsonObject) throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();;
    CinodeMarketRequestDto assignments = objectMapper.readValue(jsonObject, CinodeMarketRequestDto.class);

    return assignments;
}

public void mapDtoToEntity(CinodeMarketRequestDto cinodeMarketRequestDtoDto, com.accelerate.web.jpa.Assignment assignmentEntity) {
        assignmentEntity.setCinodeId(cinodeMarketRequestDtoDto.getMeta().getCinodeId());
        assignmentEntity.setAction(cinodeMarketRequestDtoDto.getMeta().getAction());
        assignmentEntity.setDeadlineDate(cinodeMarketRequestDtoDto.getPayload().getDeadlineDate());
        assignmentEntity.setTitle(cinodeMarketRequestDtoDto.getPayload().getTitle());
        assignmentEntity.setDescription(cinodeMarketRequestDtoDto.getPayload().getDescription());
        assignmentEntity.setAllowRemote(cinodeMarketRequestDtoDto.getPayload().isAllowRemote());
        assignmentEntity.setStartDate(cinodeMarketRequestDtoDto.getPayload().getStartDate());
        assignmentEntity.setEndDate(cinodeMarketRequestDtoDto.getPayload().getEndDate());
        assignmentEntity.setAnnouncerCompanyName(cinodeMarketRequestDtoDto.getPayload().getAnnouncerCompanyName());
        assignmentEntity.setAnnouncedDate(cinodeMarketRequestDtoDto.getPayload().getAnnouncedDate());
        fixLocation(cinodeMarketRequestDtoDto, assignmentEntity);
}

    public void fixLocation(CinodeMarketRequestDto cinodeMarketRequestDtoDto, com.accelerate.web.jpa.Assignment assignmentEntity) {
        if (cinodeMarketRequestDtoDto.getPayload().getLocation() == null) {
            assignmentEntity.setCity(StringUtils.NOT_APPLICABLE);
            assignmentEntity.setDisplayName(StringUtils.NOT_APPLICABLE);
        } else {
            if (cinodeMarketRequestDtoDto.getPayload().getLocation().getDisplayName() != null && cinodeMarketRequestDtoDto.getPayload().getLocation().getCity() != null) {
                assignmentEntity.setDisplayName(cinodeMarketRequestDtoDto.getPayload().getLocation().getDisplayName());
                assignmentEntity.setCity(cinodeMarketRequestDtoDto.getPayload().getLocation().getCity());
            }
            if (cinodeMarketRequestDtoDto.getPayload().getLocation().getDisplayName() != null && cinodeMarketRequestDtoDto.getPayload().getLocation().getCity() == null) {
                assignmentEntity.setDisplayName(cinodeMarketRequestDtoDto.getPayload().getLocation().getDisplayName());
                assignmentEntity.setCity(StringUtils.NOT_APPLICABLE);
            }
            if (cinodeMarketRequestDtoDto.getPayload().getLocation().getCity() != null && cinodeMarketRequestDtoDto.getPayload().getLocation().getDisplayName() == null) {
                assignmentEntity.setCity(cinodeMarketRequestDtoDto.getPayload().getLocation().getCity());
                assignmentEntity.setDisplayName(StringUtils.NOT_APPLICABLE);
            }
        }
    }



}
