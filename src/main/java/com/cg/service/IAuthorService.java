package com.cg.service;

import com.cg.dto.AuthorBookPublisherDTO;
import com.cg.dto.AuthorBookPublisherDto;
import com.cg.dto.AuthorRoyaltyDTO;
import com.cg.dto.BestSellingBookDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorService {
    List<AuthorBookPublisherDTO> getAuthorsWithBooksAndPublishers();
    List<AuthorBookPublisherDto> getAuthorsWithBooksAndPublishers();

    List<BestSellingBookDTO> getBestSellingBooks();

    List<AuthorRoyaltyDTO> getAuthorsWithRoyaltyRange();
}
