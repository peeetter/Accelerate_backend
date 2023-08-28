package com.accelerate.web.service.assignment;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.jpa.Assignment;
import com.accelerate.web.jpa.AssignmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
    @Autowired
    AssignmentRepository repo;

    ModelMapper modelMapper = new ModelMapper();

    public AssignmentResponse getAssignmentResponseById(Long id) {
        Optional<Assignment> assignment = repo.findById(id);
        if(assignment.isPresent()){
            Assignment assignment1 = assignment.get();
            AssignmentResponse assignmentResponse = modelMapper.map(assignment, AssignmentResponse.class);
            return assignmentResponse;
        } else {
            return null;
        }
    }

    public List<AssignmentResponse> getAllAssignments() {

        ModelMapper mapper = new ModelMapper();
        List<Assignment> assignments = new ArrayList<>();
        repo.findAll().forEach(assignments::add);
        List<AssignmentResponse> assignmentsOfDtos = mapper.map(assignments, new TypeToken<List<AssignmentResponse>>(){}.getType());
        assignmentsOfDtos.stream().forEach(s -> logger.info("Assignment with values: cinodeId: " + s.getCinodeId() + ", " + "description: " + s.getDescription() + ", " + "datelineDate: " + s.getDeadlineDate() + ", " + "title: " + s.getTitle() + ", " + "allowRemote: " + s.isAllowRemote() + ", " + "startDate: " + s.getStartDate() + ", " + "endDate: " + s.getEndDate() + ", " + "city: " + s.getCity() + ", " + "displayName: " + s.getDisplayName()));
        return compareCreatedDates(assignmentsOfDtos);
    }

    public List<AssignmentResponse> compareCreatedDates(List<AssignmentResponse> assignmentsOfDtos) {

        Collections.sort(assignmentsOfDtos, new Comparator<AssignmentResponse>() {
            @Override
            public int compare(AssignmentResponse o1, AssignmentResponse o2) {
                return o1.getAnnouncedDate().compareTo(o2.getAnnouncedDate());
            }
        });
        return assignmentsOfDtos;
    }

}
