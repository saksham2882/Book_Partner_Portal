package com.cg.repository;

import com.cg.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepo extends JpaRepository<Authors, String> {

    //queries
}
