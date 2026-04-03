package com.cg.service.impl;


import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.MultiAuthorTitlesDTO;
import com.cg.dto.TitleSalesByStoreDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repository.ITitleRepo;
import com.cg.service.ITitleService;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TitleServiceImpl implements ITitleService {

    private final ITitleRepo titleRepository;

    public TitleServiceImpl(ITitleRepo titleRepository) {
        this.titleRepository = titleRepository;
    }


    // API 2:
    @Override
    public List<TitleSalesByStoreDTO> getAllTitlesWithSalesByStore() {
        List<TitleSalesByStoreDTO> result = titleRepository.findAllTitlesWithSalesByStore();

        if(result.isEmpty()){
            throw new ResourceNotFoundException("No sales data found for any title.");
        }
        return result;
    }


    // API 3:
    @Override
    public List<AuthorTitlesUnderPriceDTO> getTitlesByMaxPrice(Double maxPrice) {

        if (maxPrice < 0) {
            throw new IllegalArgumentException("maxPrice cannot be negative.");
        }

        List<AuthorTitlesUnderPriceDTO> result = titleRepository.findTitlesUnderPrice(maxPrice);

        if(result.isEmpty()){
            throw new ResourceNotFoundException("No books found under price: " + maxPrice);
        }
        return result;
    }


    // API 9:
    @Override
    public List<MultiAuthorTitlesDTO> getMultiAuthorTitles() {
        List<Object[]> results = titleRepository.findMultiAuthorTitles();
        List<MultiAuthorTitlesDTO> dtos = new ArrayList<>();

        for (Object[] row : results) {
            String titleId = (String) row[0];
            String title = (String) row[1];
            String type = (String) row[2];
            Long authorCount = ((Number) row[3]).longValue();
            List<String> authorNames = Arrays.asList(((String) row[4]).split(", "));

            dtos.add(new MultiAuthorTitlesDTO(titleId, title, type, authorCount, authorNames));
        }

        return dtos;
    }
}