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
                s.storeId, s.storeName, s.city,
                sa.qty, sa.ordDate
          )
          FROM Title t
          JOIN t.sales sa
          JOIN sa.store s
          ORDER BY t.title ASC, s.storeName ASC
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


  // API 9:
  @Query(value = """
	    SELECT 
	        t.title_id AS titleId, t.title AS title, t.type AS type,
            COUNT(ta.au_id) AS authorCount,
            GROUP_CONCAT(CONCAT(a.au_fname, ' ', a.au_lname) SEPARATOR ', ') AS authorNames
        FROM titles t
        JOIN titleauthor ta ON t.title_id = ta.title_id
        JOIN authors a ON ta.au_id = a.au_id
        GROUP BY t.title_id, t.title, t.type
        HAVING COUNT(ta.au_id) > 1
    """, nativeQuery = true)
  List<Object[]> findMultiAuthorTitles();
}
