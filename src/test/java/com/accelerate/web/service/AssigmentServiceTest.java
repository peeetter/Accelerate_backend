package com.accelerate.web.service;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.service.assignment.AssignmentService;
import com.accelerate.web.service.cinode.CinodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AssigmentServiceTest {

    @Autowired
    AssignmentService service;

    @Test
    void sortData() throws ParseException {

        Date date1 = getDateToSortMethod("2023-10-01");
        Date date2 = getDateToSortMethod("2023-12-23");
        Date date3 = getDateToSortMethod("2023-11-02");
        Date date4 = getDateToSortMethod("2023-10-05");
        Date date5 = getDateToSortMethod("2023-05-01");

        AssignmentResponse assignmentResponse = new AssignmentResponse(1, "Desc", getDateToSortMethod("2023-07-01"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", date1);
        AssignmentResponse assignmentResponse2 = new AssignmentResponse(1, "Desc", getDateToSortMethod("2023-12-23"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", date2);
        AssignmentResponse assignmentResponse3 = new AssignmentResponse(1, "Desc", getDateToSortMethod("2023-11-02"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", date3);
        AssignmentResponse assignmentResponse4 = new AssignmentResponse(1, "Desc", getDateToSortMethod("2023-02-05"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", date4);
        AssignmentResponse assignmentResponse5 = new AssignmentResponse(1, "Desc", getDateToSortMethod("2023-05-01"), "Title",
                true, getDateToSortMethod("2023-10-01"), getDateToSortMethod("2023-10-01"), "City", "DisplayName", date5);
        List<AssignmentResponse> assignmentResponseList = new ArrayList<>();
        assignmentResponseList.add(assignmentResponse);
        assignmentResponseList.add(assignmentResponse2);
        assignmentResponseList.add(assignmentResponse3);
        assignmentResponseList.add(assignmentResponse4);
        assignmentResponseList.add(assignmentResponse5);

        List<AssignmentResponse> sortedList = service.sortCreatedDates(assignmentResponseList);
        assertEquals(sortedList.get(0).getAnnouncedDate(), date5);
        assertEquals(sortedList.get(1).getAnnouncedDate(), date1);
        assertEquals(sortedList.get(2).getAnnouncedDate(), date4);
        assertEquals(sortedList.get(3).getAnnouncedDate(), date3);
        assertEquals(sortedList.get(4).getAnnouncedDate(), date2);
    }

    public Date getDateToSortMethod(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}
