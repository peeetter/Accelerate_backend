package com.accelerate.web.service;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.jpa.Assignment;
import com.accelerate.web.jpa.AssignmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository repository;

    ModelMapper modelMapper = new ModelMapper();


    public String decideDbMethodBasedOnAction (Assignment assignmentEntity) {

        if (assignmentEntity.getAction().equals("Created") || assignmentEntity.getAction().equals("Updated")) {
            Long cinodeId = (long) assignmentEntity.getCinodeId();
            System.out.println("cinodeId-->: " + cinodeId);
            Optional<Assignment> assignment = repository.findById(cinodeId);

            if (assignment.isEmpty()) {
                 Assignment createdEntity = repository.save(assignmentEntity);
                 return "Entity " + createdEntity.getAction() + " with id: " + createdEntity.getCinodeId();
            } else {
                Assignment updatedEntity = repository.save(assignmentEntity);
                return "Entity " + updatedEntity.getAction() + " with id: " + updatedEntity.getCinodeId();
            }
        }

        if (assignmentEntity.getAction().equals("Deleted")) {
            Long cinodeId = (long) assignmentEntity.getCinodeId();
            Optional<Assignment> assignment = repository.findById(cinodeId);
            if (assignment != null) {
                repository.delete(assignmentEntity);
            }
            return "Assignment with cinodeId: " + assignmentEntity.getCinodeId() + " is " + assignmentEntity.getAction();
        }
        /*
        1. Om deleted. --> findById --> Delete
        2. Om Created --> findById --> Om inte existerar lägg till.
        3. Om updated --> findByid --> Save
         */
        return "Något är skumt";
    }

    public AssignmentResponse getAssignmentResponseById(Long id) {
        Optional<Assignment> assignment = repository.findById(id);
        if(assignment.isPresent()){
            Assignment assignment1 = assignment.get();
            AssignmentResponse assignmentResponse = modelMapper.map(assignment, AssignmentResponse.class);
            return assignmentResponse;
        } else {
            return null;
        }
    }
}
