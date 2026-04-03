package com.cg.repository;

import com.cg.dto.TopPublisherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Publishers;

import java.util.List;

/*
 * @author Siddhant
*/
@Repository
public interface IPublisherRepo extends JpaRepository<Publishers,String>{

    @Query("Select new com.cg.dto.TopPublisherDTO(p.pubId,p.pubName,COALESCE(SUM(s.qty),0),COALESCE(SUM(s.qty*t.price),0)) FROM Publishers p LEFT JOIN p.titles t LEFT JOIN t.sales s GROUP BY p.pubId, p.pubName ORDER BY COALESCE(SUM(s.qty*t.price),0) DESC")
    List<TopPublisherDTO> findTopPerformingPublishers();
}
