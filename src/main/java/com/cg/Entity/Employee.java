package com.cg.Entity;

import com.cg.Entity.Job;
import com.cg.Entity.Publishers;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.concurrent.Flow;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id", length = 10, nullable = false)
    private String empId;
    @Column(name = "fname", length = 20, nullable = false)
    private String firstName;

    @Column(name = "minit", length = 1)
    private String middleInitial;

    @Column(name = "lname", length = 30, nullable = false)
    private String lastName;

    @Column(name = "job_lvl")
    private Integer jobLevel = 10;

    @Column(name = "hire_date", nullable = false)
    private LocalDateTime hireDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_id", nullable = false)
    private Publishers publisher;
    public Employee() {}

    public Employee(String empId,
                    String firstName,
                    String middleInitial,
                    String lastName,
                    Integer jobLevel,
                    LocalDateTime hireDate,
                    Job job,
                    Publishers publisher) {

        this.empId = empId;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.jobLevel = jobLevel;
        this.hireDate = hireDate;
        this.job = job;
        this.publisher = publisher;
    }

    public String getEmpId() { return empId; }

    public void setEmpId(String empId) { this.empId = empId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleInitial() { return middleInitial; }

    public void setMiddleInitial(String middleInitial) { this.middleInitial = middleInitial; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getJobLevel() { return jobLevel; }

    public void setJobLevel(Integer jobLevel) { this.jobLevel = jobLevel; }

    public LocalDateTime getHireDate() { return hireDate; }

    public void setHireDate(LocalDateTime hireDate) { this.hireDate = hireDate; }

    public Job getJob() { return job; }

    public void setJob(Job job) { this.job = job; }

    public Publishers getPublisher() { return publisher; }

    public void setPublisher(Publishers publisher) { this.publisher = publisher; }
}
