package com.cg.Repository;

import com.cg.Entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorsRepo extends JpaRepository<Authors, String> {

    //queries
}
