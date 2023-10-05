package com.accelerate.web.api.assignment;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.service.assignment.AssignmentService;
import com.accelerate.web.service.cinode.CinodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController {

    @Autowired
    AssignmentService service;

    @GetMapping(path="/getAssignment/{id}")
    @CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    public ResponseEntity<AssignmentResponse> getAssignmentById(@PathVariable("id")  Long id) {
        AssignmentResponse assignmentResponse = service.getAssignmentResponseById(id);
        return ResponseEntity.ok(assignmentResponse);
    }

    @GetMapping(path="/getAllAssignments")
    @CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments() {
        List<AssignmentResponse> assignmentResponses = service.getAllAssignments();
        return ResponseEntity.ok(assignmentResponses);
    }
}
