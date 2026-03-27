package com.cg.Repository;

import com.cg.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepo extends JpaRepository<Job, String> {
    //Queries
}
