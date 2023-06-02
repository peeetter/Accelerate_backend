package com.accelerate.web.mapper;

import com.accelerate.web.dto.Assignment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class WebhookJsonMapper {

public Assignment mapWebhookToJson(String jsonObject) throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();;
    Assignment assignments = objectMapper.readValue(jsonObject, Assignment.class);

    return assignments;
}

}
