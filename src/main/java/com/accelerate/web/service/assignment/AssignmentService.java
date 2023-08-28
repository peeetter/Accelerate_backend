package com.accelerate.web.service.assignment;


import com.accelerate.web.dto.AssignmentResponse;

import java.util.List;

public interface AssignmentService  {
    List<AssignmentResponse> getAllAssignments();

    AssignmentResponse getAssignmentResponseById(Long id);
}
