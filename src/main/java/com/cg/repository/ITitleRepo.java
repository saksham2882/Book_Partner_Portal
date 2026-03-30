package com.cg.repository;

import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.TitleSalesByStoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Title;

import java.util.List;


@Repository
public interface ITitleRepo extends JpaRepository<Title, String> {

    // API 2:
    @Query("""
            SELECT new com.cg.dto.TitleSalesByStoreDTO(
                  t.titleId, t.title, t.type, t.price,
                  s.storId, s.storName, s.city,
                  sa.qty, sa.ordDate
            )
            FROM Title t
            JOIN t.sales sa
            JOIN sa.store s
            ORDER BY t.title ASC, s.storName ASC
        """)
    List<TitleSalesByStoreDTO> findAllTitlesWithSalesByStore();



    // API 3:
    @Query("""
         SELECT new com.cg.dto.AuthorTitlesUnderPriceDTO(
              t.titleId, t.title, t.type, t.price, t.royalty, t.ytdSales,
              a.auId, CONCAT(a.FirstName, ' ', a.LastName),
              ta.auOrd, ta.royaltyPer
         )
         FROM Title t
         JOIN t.titleAuthors ta
         JOIN ta.authors a
         WHERE t.price IS NOT NULL
           AND t.price <= :maxPrice
         ORDER BY t.price ASC, t.title ASC, ta.auOrd ASC
    """)
    List<AuthorTitlesUnderPriceDTO> findTitlesUnderPrice(@Param("maxPrice") Double maxPrice);
}

