package com.accelerate.web.controller;

import com.accelerate.web.MockJsonProcessingException;
import com.accelerate.web.api.cinode.CinodeController;
import com.accelerate.web.service.cinode.CinodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CinodeControllerTest {

    @Mock
    CinodeService service;

    @InjectMocks
    CinodeController controller;

    private static final String REQUEST = "{\"Version\":\"v1\",\"WebhookId\":\"0e66913c-c309-ee11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"7473\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":7473,\"DeadlineDate\":\"2023-06-13T10:21:00\",\"Title\":\"Test\",\"Description\":\"Hej hej är kommer en beskrivning på roll\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-06-13T08:22:07.0560719\",\"StartDate\":null,\"EndDate\":null,\"MarketURL\":\"https://cinode.market/requests/7473\",\"Skills\":[{\"KeywordId\":29796,\"Name\":\"React.js\",\"Description\":\".\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Örebro, Sverige\",\"City\":\"Alingsås\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";
    private static final String MOCKED_RESPONSE = "mockedResponse";
    @Test
    void getCinodeMarketNotificationWithStatusOk() throws JsonProcessingException {


        when(service.handleRequestFromCinode(any()))
                .thenReturn(MOCKED_RESPONSE);

        ResponseEntity<String> response = controller.getCinodeMarketNotification(REQUEST);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(MOCKED_RESPONSE, response.getBody());
    }

    @Test
    void getCinodeMarketNotificationWithStatusNotOk() throws JsonProcessingException {

        doThrow(new MockJsonProcessingException("Mocked exception")).when(service).handleRequestFromCinode(REQUEST);

        assertThrows(JsonProcessingException.class, () -> {
            controller.getCinodeMarketNotification(REQUEST);
        });

    }
}
