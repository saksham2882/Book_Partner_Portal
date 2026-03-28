package com.cg.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 00Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Short jobId;


    @Column(name = "job_desc", length = 50, nullable = false)
    private String jobDesc =
            "New Position - title not formalized yet";


    @Column(name = "min_lvl", nullable = false)
    private Integer minLvl;


    @Column(name = "max_lvl", nullable = false)
    private Integer maxLvl;

    @JsonIgnore
    @OneToMany(
            mappedBy = "job",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Employee> employees;
}
