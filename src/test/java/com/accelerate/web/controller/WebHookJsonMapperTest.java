package com.accelerate.web.controller;

import com.accelerate.web.TestData.TestData;
import com.accelerate.web.dto.CinodeMarketRequest;
import com.accelerate.web.dto.CinodeMarketRequestDto;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.modelmapper.ModelMapper;

import java.util.Date;

class WebHookJsonMapperTest {

    TestData testData = new TestData();
    WebhookJsonMapper mapper = new WebhookJsonMapper();


    @Test
    void testTestDataAllFieldsWhenLocationIsNull() throws JsonProcessingException {
        String jsonString = "{\"Version\":\"v1\",\"WebhookId\":\"0e66913c-c309-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7473\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7473,\"DeadlineDate\":\"2023-06-13T10:21:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-13T08:22:07.0560719\",\"StartDate\":null,\"EndDate\":null,\"MarketURL\":\"https://cinode.market/requests/7473\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":null,\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";
        CinodeMarketRequestDto webHookJson = testData.getDto(jsonString);

        com.accelerate.web.jpa.Assignment assignmentEntity = new com.accelerate.web.jpa.Assignment();
        mapper.mapDtoToEntity(webHookJson, assignmentEntity);

        int expectedCinodeId = webHookJson.getCinodeId();
        String expectedAction = webHookJson.getAction();
        Date expectedDeadlineDate = webHookJson.getDeadlineDate();
        String expectedTitle = webHookJson.getTitle();
        String expectedDescription = webHookJson.getDescription();
        Boolean expectedAllowRemote = webHookJson.isAllowRemote();
        Date expectedStartDate = webHookJson.getStartDate();
        Date expectedEndDate = webHookJson.getEndDate();
        Date announcedDate = webHookJson.getAnnouncedDate();
        String announcerCompanyName = webHookJson.getAnnouncerCompanyName();
        String expectedCity = "n/a";
        String expectedDisplayName = "n/a";

        assertEquals(expectedCinodeId, assignmentEntity.getCinodeId());
        assertEquals(expectedAction, assignmentEntity.getAction());
        assertEquals(expectedDeadlineDate, assignmentEntity.getDeadlineDate());
        assertEquals(expectedTitle, assignmentEntity.getTitle());
        assertEquals(expectedDescription, assignmentEntity.getDescription());
        assertEquals(expectedAllowRemote, assignmentEntity.isAllowRemote());
        assertEquals(expectedStartDate, assignmentEntity.getStartDate());
        assertEquals(expectedEndDate, assignmentEntity.getEndDate());
        assertEquals(announcedDate, assignmentEntity.getAnnouncedDate());
        assertEquals(announcerCompanyName, assignmentEntity.getAnnouncerCompanyName());
        assertEquals(expectedCity, assignmentEntity.getCity());
        assertEquals(expectedDisplayName, assignmentEntity.getDisplayName());

    }

    @Test
    void testMappingWhenDatesArePresent() throws JsonProcessingException {
        String jsonString = "{\"Version\":\"v1\",\"WebhookId\":\"2bc00939-b40a-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7490\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7490,\"DeadlineDate\":\"2023-08-01T00:00:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-14T13:07:10.4755756\",\"StartDate\":\"2023-08-01T00:00:00\",\"EndDate\":\"2023-12-31T00:00:00\",\"MarketURL\":\"https://cinode.market/requests/7490\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Stockholm, Sverige\",\"City\":\"Stockholm\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";
        CinodeMarketRequestDto webHookJson = testData.getDto(jsonString);
        com.accelerate.web.jpa.Assignment assignmentEntity = new com.accelerate.web.jpa.Assignment();
        mapper.mapDtoToEntity(webHookJson, assignmentEntity);

        int expectedCinodeId = webHookJson.getCinodeId();
        String expectedAction = webHookJson.getAction();
        Date expectedDeadlineDate = webHookJson.getDeadlineDate();
        String expectedTitle = webHookJson.getTitle();
        String expectedDescription = webHookJson.getDescription();
        Boolean expectedAllowRemote = webHookJson.isAllowRemote();
        Date expectedStartDate = webHookJson.getStartDate();
        Date expectedEndDate = webHookJson.getEndDate();
        Date announcedDate = webHookJson.getAnnouncedDate();
        String announcerCompanyName = webHookJson.getAnnouncerCompanyName();
        String expectedCity = webHookJson.getCity();
        String expectedDisplayName = webHookJson.getDisplayName();


        assertEquals(expectedCinodeId, assignmentEntity.getCinodeId());
        assertEquals(expectedAction, assignmentEntity.getAction());
        assertEquals(expectedDeadlineDate, assignmentEntity.getDeadlineDate());
        assertEquals(expectedTitle, assignmentEntity.getTitle());
        assertEquals(expectedDescription, assignmentEntity.getDescription());
        assertEquals(expectedAllowRemote, assignmentEntity.isAllowRemote());
        assertEquals(expectedStartDate, assignmentEntity.getStartDate());
        assertEquals(expectedEndDate, assignmentEntity.getEndDate());
        assertEquals(announcedDate, assignmentEntity.getAnnouncedDate());
        assertEquals(announcerCompanyName, assignmentEntity.getAnnouncerCompanyName());
        assertEquals(expectedCity, assignmentEntity.getCity());
        assertEquals(expectedDisplayName, assignmentEntity.getDisplayName());
    }

    @Test
    void testMappingWhenCityAndDisplayNameIsPresent() throws JsonProcessingException {

        String jsonString = "{\"Version\":\"v1\",\"WebhookId\":\"0e66913c-c309-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7473\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7473,\"DeadlineDate\":\"2023-06-13T10:21:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-13T08:22:07.0560719\",\"StartDate\":null,\"EndDate\":null,\"MarketURL\":\"https://cinode.market/requests/7473\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Örebro, Sverige\",\"City\":\"Alingsås\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";

        CinodeMarketRequestDto webHookJson = testData.getDto(jsonString);

        com.accelerate.web.jpa.Assignment assignmentEntity = new com.accelerate.web.jpa.Assignment();
        mapper.mapDtoToEntity(webHookJson, assignmentEntity);

        String expectedCity = webHookJson.getCity();
        String expectedDisplayName = webHookJson.getDisplayName();


        assertEquals(expectedCity, assignmentEntity.getCity());
        assertEquals(expectedDisplayName, assignmentEntity.getDisplayName());
    }

    @Test
    void testMappingWhenCityIsPresentButDisplayNameIsNull() throws JsonProcessingException {

        String jsonString = "{\"Version\":\"v1\",\"WebhookId\":\"0e66913c-c309-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7473\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7473,\"DeadlineDate\":\"2023-06-13T10:21:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-13T08:22:07.0560719\",\"StartDate\":null,\"EndDate\":null,\"MarketURL\":\"https://cinode.market/requests/7473\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":null,\"City\":\"Alingsås\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";

        CinodeMarketRequestDto webHookJson = testData.getDto(jsonString);

        com.accelerate.web.jpa.Assignment assignmentEntity = new com.accelerate.web.jpa.Assignment();
        mapper.mapDtoToEntity(webHookJson, assignmentEntity);

        String expectedCity = webHookJson.getCity();
        String expectedDisplayName = "n/a";


        assertEquals(expectedCity, assignmentEntity.getCity());
        assertEquals(expectedDisplayName, assignmentEntity.getDisplayName());
    }
    @Test
    void testMappingWhenDisplayNameIsPresentButCityIsNull() throws JsonProcessingException {

        String jsonString = "{\"Version\":\"v1\",\"WebhookId\":\"0e66913c-c309-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7473\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7473,\"DeadlineDate\":\"2023-06-13T10:21:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-13T08:22:07.0560719\",\"StartDate\":null,\"EndDate\":null,\"MarketURL\":\"https://cinode.market/requests/7473\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Norrköping, Sweden\",\"City\":null,\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";

        CinodeMarketRequestDto webHookJson = testData.getDto(jsonString);

        com.accelerate.web.jpa.Assignment assignmentEntity = new com.accelerate.web.jpa.Assignment();
        mapper.mapDtoToEntity(webHookJson, assignmentEntity);


        String expectedCity = "n/a";
        String expectedDisplayName = webHookJson.getDisplayName();


        assertEquals(expectedCity, assignmentEntity.getCity());
        assertEquals(expectedDisplayName, assignmentEntity.getDisplayName());
    }
}