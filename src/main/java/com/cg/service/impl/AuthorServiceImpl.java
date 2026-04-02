package com.cg.service.impl;

import com.cg.dto.AuthorRoyaltyDTO;
import com.cg.dto.BestSellingBookDTO;
import com.cg.entity.Title;
import com.cg.entity.TitleAuthor;
import com.cg.exception.InvalidDataException;
import com.cg.service.IAuthorService;
import com.cg.dto.AuthorBookPublisherDTO;
import com.cg.repository.IAuthorRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//@author Deependra
@Service
@Transactional
public class AuthorServiceImpl implements IAuthorService {
    private final IAuthorRepo authorRepository;

    public AuthorServiceImpl(IAuthorRepo authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorBookPublisherDTO> getAuthorsWithBooksAndPublishers() {
        return authorRepository.findAll().stream().map(author -> {
            AuthorBookPublisherDTO dto = new AuthorBookPublisherDTO();
            dto.setAuthorId(author.getAuId());
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorLastName(author.getLastName());

            List<AuthorBookPublisherDTO.BookPublisherDetail> books = new ArrayList<>();
            if (author.getTitleAuthors() != null) {
                for (TitleAuthor ta : author.getTitleAuthors()) {
                    if (ta.getTitle() != null) {
                        Title title = ta.getTitle();
                        String pubId = title.getPublisher() != null ? title.getPublisher().getPubId() : null;
                        String pubName = title.getPublisher() != null ? title.getPublisher().getName() : null;
                        books.add(new AuthorBookPublisherDTO.BookPublisherDetail(
                                title.getTitleId(), title.getTitle(), pubId, pubName
                        ));
                    }
                }
            }
            dto.setBooks(books);
            return dto;
        }).collect(Collectors.toList());
    }


    //  API 5
    public List<BestSellingBookDTO> getBestSellingBooks() {

//        if (true) {
//            throw new InvalidDataException("Forced validation error");
//        }
        List<BestSellingBookDTO> list = authorRepository.findBestSellingBooks();

        Map<String, BestSellingBookDTO> map = new HashMap<>();

        for (BestSellingBookDTO dto : list) {
            //  VALIDATION
            if (dto.getAuthorId() == null || dto.getTitleName() == null) {
                throw new RuntimeException("Invalid best-selling book data");
            }

            if (dto.getTotalSales() == null || dto.getTotalSales() < 0) {
                throw new RuntimeException("Invalid sales value");
            }

            if (dto.getRevenue() == null || dto.getRevenue() < 0) {
                throw new RuntimeException("Invalid revenue value");
            }

            map.putIfAbsent(dto.getAuthorId(), dto);
        }


        return new ArrayList<>(map.values());
    }

    //  API 6
    public List<AuthorRoyaltyDTO> getAuthorsWithRoyaltyRange() {

//        if (true) {
//            throw new InvalidDataException("Forced validation error");
//        }
        List<AuthorRoyaltyDTO> list = authorRepository.findAuthorsWithRoyaltyRange();

        for (AuthorRoyaltyDTO dto : list) {

            //  VALIDATION
            if (dto.getAuthorId() == null || dto.getTitleName() == null) {
                throw new RuntimeException("Invalid royalty data");
            }

            if (dto.getMinRoyalty() == null || dto.getMaxRoyalty() == null) {
                throw new RuntimeException("Royalty values cannot be null");
            }

            if (dto.getMinRoyalty() > dto.getMaxRoyalty()) {
                throw new RuntimeException("Invalid royalty range");
            }
        }

        return list;

    }
}