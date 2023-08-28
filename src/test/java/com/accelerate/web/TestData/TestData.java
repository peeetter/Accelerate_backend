package com.accelerate.web.TestData;


import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TestData {

    WebhookJsonMapper mapper = new WebhookJsonMapper();

    public String getTestDataFromWebHook (String jsonString) {
        String testString = jsonString;

        return testString;
    }

    public CinodeMarketRequestDto getDto(String json) throws JsonProcessingException {
        String testString = getTestDataFromWebHook(json);
        CinodeMarketRequestDto cinodeMarketRequestDtoDto = mapper.mapWebhookJsonToDto(testString);
        return cinodeMarketRequestDtoDto;
    }

    /*
    public List<Assignment> getListOfDtos(List<String> assignments) {
        //Här ska mappa en lista stränga til dtos.
    }
     */

}
