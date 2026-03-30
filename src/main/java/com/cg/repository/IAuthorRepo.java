package com.cg.repository;

import com.cg.dto.AuthorRoyaltyDTO;
import com.cg.dto.BestSellingBookDTO;
import com.cg.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepo extends JpaRepository<Authors, String> {

    //queries
    @Query("""
SELECT new com.cg.dto.BestSellingBookDTO(
    a.auId,
    CONCAT(a.FirstName, ' ', a.LastName),
    t.titleId,
    t.title,
    SUM(s.qty),
    SUM(s.qty * t.price)
)
FROM Authors a
JOIN a.titleAuthors ta
JOIN ta.title t
JOIN t.sales s
GROUP BY a.auId, a.FirstName, a.LastName, t.titleId, t.title
ORDER BY SUM(s.qty) DESC
""")
    List<BestSellingBookDTO> findBestSellingBooks();


    @Query("""
SELECT new com.cg.dto.AuthorRoyaltyDTO(
    a.auId,
    CONCAT(a.FirstName, ' ', a.LastName),
    t.titleId,
    t.title,
    MIN(r.royalty),
    MAX(r.royalty)
)
FROM Authors a
JOIN a.titleAuthors ta
JOIN ta.title t
JOIN t.royschedList r
GROUP BY a.auId, a.FirstName, a.LastName, t.titleId, t.title
""")
    List<AuthorRoyaltyDTO> findAuthorsWithRoyaltyRange();
}
