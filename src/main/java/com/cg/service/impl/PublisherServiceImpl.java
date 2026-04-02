package com.cg.service.impl;

import java.util.List;
import com.cg.exception.BadRequestException;
import com.cg.exception.ResourceNotFoundException;
import java.util.Collections;
import java.util.concurrent.Flow;
import com.cg.entity.Publishers;
import com.cg.dto.EmployeeWithJobDTO;
import com.cg.dto.JobDTO;
import com.cg.dto.TopPublisherDTO;
import com.cg.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dto.PublisherEmployeeResponseDTO;
import com.cg.repository.*;
import lombok.*;
import com.cg.entity.*;
/*
* @author Siddhant*/
@Service
public class PublisherServiceImpl implements PublisherService {
    private final IEmployeeRepo erepo;
    private final IPublisherRepo prepo;
    public PublisherServiceImpl(IEmployeeRepo erepo,IPublisherRepo prepo) {

        this.erepo=erepo;
        this.prepo=prepo;
    }

    @Override
    public PublisherEmployeeResponseDTO getEmployeesWithJobs(String pubId) {
        if (pubId == null || pubId.trim().isEmpty()) {
            throw new BadRequestException("Publisher ID cannot be null or empty");
        }
        if (!pubId.matches("\\d{4}")) {
            throw new BadRequestException("Publisher ID must be 4 digits");
        }
        Publishers publisher = prepo.findById(pubId).orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + pubId));
        List<Employee> e=erepo.findEmployeesWithJobsByPublisherId(pubId);
        if (e.isEmpty()) {
            return PublisherEmployeeResponseDTO.builder().pubId(pubId)
                    .pubName(publisher.getPubName())
                    .employees(Collections.emptyList())
                    .build();
        }
        String pubName =e.get(0).getPublisher().getPubName();
        List<EmployeeWithJobDTO> employeeDTOs = e.stream()
                .map(emp -> EmployeeWithJobDTO.builder()
                        .empId(emp.getEmpId())
                        .firstName(emp.getFirstName())
                        .middleInitial(emp.getMiddleInitial())
                        .lastName(emp.getLastName())
                        .jobLevel(emp.getJobLevel())
                        .hireDate(emp.getHireDate())
                        .job(   emp.getJob() != null ?
                                JobDTO.builder()
                                        .jobId(emp.getJob().getJobId())
                                        .jobDesc(emp.getJob().getJobDesc())
                                        .minLvl(emp.getJob().getMinLvl())
                                        .maxLvl(emp.getJob().getMaxLvl())
                                        .build():null
                        )
                        .build()
                )
                .toList();
        return PublisherEmployeeResponseDTO.builder()
                .pubId(pubId)
                .pubName(pubName)
                .employees(employeeDTOs)
                .build();
    }
    @Override
    public List<TopPublisherDTO> getTopPerformingPublishers() {
        return prepo.findTopPerformingPublishers();
    }

}

