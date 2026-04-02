package com.cg.Book_Partner_Portal;

import com.cg.dto.PublisherEmployeeResponseDTO;
import com.cg.dto.TopPublisherDTO;
import com.cg.entity.Employee;
import com.cg.entity.Job;
import com.cg.entity.Publishers;
import com.cg.exception.BadRequestException;
import com.cg.repository.IEmployeeRepo;
import com.cg.repository.IPublisherRepo;
import com.cg.service.impl.PublisherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {
    @Mock
    private IEmployeeRepo employeeRepository;
    @Mock
    private IPublisherRepo publisherRepository;
    @InjectMocks
    private PublisherServiceImpl service;
    /* @author Siddhant
     Made positive and Negative Test cases for api Number 4-/api/publishers/{pub_id}/employees-with-jobs */
    //Positive Test Case for API Number 4
    @Test
    void positivetestGetEmployeesWithJobs() {
        String pubId="1234";
        Publishers publisher = new Publishers();
        publisher.setPubId(pubId);
        publisher.setPubName("ABC Publications");
        Job job = new Job();
        job.setJobId((short) 1);
        job.setJobDesc("Developer");
        job.setMinLvl(1);
        job.setMaxLvl(5);
        Employee emp = new Employee();
        emp.setEmpId("E1");
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setMiddleInitial("A");
        emp.setJobLevel(2);
        emp.setHireDate(LocalDateTime.now());
        emp.setJob(job);
        emp.setPublisher(publisher);
        when(publisherRepository.findById(pubId)).thenReturn(Optional.of(publisher));
        when(employeeRepository.findEmployeesWithJobsByPublisherId(pubId)).thenReturn(List.of(emp));
        PublisherEmployeeResponseDTO response=service.getEmployeesWithJobs(pubId);
        assertNotNull(response);
        assertEquals(pubId, response.getPubId());
        assertEquals("ABC Publications", response.getPubName());
        assertEquals(1, response.getEmployees().size());
    }
    //Negative Test Case for API Number 4
    @Test
    void negativetestGetEmployeesWithJobs() {

        String pubId = "12";

        assertThrows(BadRequestException.class, () ->
                service.getEmployeesWithJobs(pubId));
    }
    /* @author Siddhant
    Made positive and negative  Test cases for API Number 7 /api/publishers/top-performing*/
    //Positive Test Case for API Number 7
    @Test
    void positivetestGetTopPerformingPublishers() {
        TopPublisherDTO dto = new TopPublisherDTO();
        when(publisherRepository.findTopPerformingPublishers()).thenReturn(List.of(dto));
        List<TopPublisherDTO> dtoresult = service.getTopPerformingPublishers();
        assertNotNull(dtoresult);
        assertEquals(1,dtoresult.size());
    }
    @Test
    void negativetestGetTopPerformingPublishers() {
        when(publisherRepository.findTopPerformingPublishers()).thenReturn(Collections.emptyList());
        List<TopPublisherDTO> result = service.getTopPerformingPublishers();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
