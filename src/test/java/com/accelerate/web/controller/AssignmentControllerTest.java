package com.accelerate.web.controller;


import com.accelerate.web.TestData.TestData;
import com.accelerate.web.api.assignment.AssignmentController;
import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.service.assignment.AssignmentService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssignmentControllerTest {


    @InjectMocks
    private AssignmentController assignmentController;

    TestData testData = new TestData();

    @Mock
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAssignmentById() throws ParseException {
        // Given

        AssignmentResponse expectedResponse = testData.getAssignmentResponse(1, "2023-10-01");
        long l = expectedResponse.getCinodeId();
        when(assignmentService.getAssignmentResponseById(l)).thenReturn(expectedResponse);

        ResponseEntity<AssignmentResponse> response = assignmentController.getAssignmentById(l);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());

        verify(assignmentService, times(1)).getAssignmentResponseById(l);
    }

    @Test
    void testGetAllAssignments() throws ParseException {
        AssignmentResponse response1 = testData.getAssignmentResponse(1, "2023-10-01");
        AssignmentResponse response2 = testData.getAssignmentResponse(2, "2023-10-20");
        AssignmentResponse response3 = testData.getAssignmentResponse(3, "2023-07-03");

        List<AssignmentResponse> responseList = Arrays.asList(response1, response2, response3);

        when(assignmentService.getAllAssignments()).thenReturn(responseList);

        ResponseEntity<List<AssignmentResponse>> response = assignmentController.getAllAssignments();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(responseList.size(), response.getBody().size());
        assertEquals(responseList, response.getBody());
    }
}
