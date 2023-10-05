package com.accelerate.web.TestData;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestData {

    WebhookJsonMapper mapper = new WebhookJsonMapper();

    public String getTestDataFromWebHook (String jsonString) {
        return jsonString;
    }

    public CinodeMarketRequestDto getDto(String json) throws JsonProcessingException {
        String testString = getTestDataFromWebHook(json);
        CinodeMarketRequestDto cinodeMarketRequestDto = mapper.mapWebhookJsonToCinodeMarketRequest(testString);
        return cinodeMarketRequestDto;
    }


    public AssignmentResponse getAssignmentResponse(int cinodId, String createdDate) throws ParseException {
        AssignmentResponse assignmentResponse = new AssignmentResponse(cinodId, "Desc", getDateToSortMethod("2023-07-01"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", getDateToSortMethod(createdDate));
        return assignmentResponse;
    }


    public Date getDateToSortMethod(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}
