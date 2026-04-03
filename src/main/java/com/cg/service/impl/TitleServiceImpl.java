package com.cg.service.impl;


import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.MultiAuthorTitlesDTO;
import com.cg.dto.TitleSalesByStoreDTO;
import com.cg.dto.TitleSalesResponseDTO;
import com.cg.exception.InvalidDataException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repository.ITitleRepo;
import com.cg.service.ITitleService;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@Service
public class TitleServiceImpl implements ITitleService {

    private final ITitleRepo titleRepository;

    public TitleServiceImpl(ITitleRepo titleRepository) {
        this.titleRepository = titleRepository;
    }


    // API 2:
    @Override
    public List<TitleSalesResponseDTO> getAllTitlesWithSalesByStore() {
        List<TitleSalesByStoreDTO> list = titleRepository.findAllTitlesWithSalesByStore();

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No sales data found for any title.");
        }

        Map<String, TitleSalesResponseDTO> map = new LinkedHashMap<>();

        for (TitleSalesByStoreDTO row : list) {
            String titleId = row.getTitleId();

            if (!map.containsKey(titleId)) {
                map.put(titleId, new TitleSalesResponseDTO(
                        row.getTitleId(),
                        row.getTitle(),
                        row.getType(),
                        row.getPrice(),
                        new ArrayList<>()
                ));
            }
            TitleSalesResponseDTO.StoreSaleInfo storeInfo =
                    new TitleSalesResponseDTO.StoreSaleInfo(
                            row.getStoreId(),
                            row.getStoreName(),
                            row.getStoreCity(),
                            row.getQuantitySold()
                    );

            map.get(titleId).getSaleInfo().add(storeInfo);
        }
        return new ArrayList<>(map.values());
    }


    // API 3:
    @Override
    public List<AuthorTitlesUnderPriceDTO> getTitlesUnderPrice(Double maxPrice) {

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

        if (results == null) {
            throw new IllegalArgumentException("Database returned null result");
        }
        if (results.isEmpty()) {
            throw new ResourceNotFoundException("No multi-author titles found");
        }

        List<MultiAuthorTitlesDTO> dtos = new ArrayList<>();

        for (Object[] row : results) {
            if (row == null || row.length < 5) {
                throw new InvalidDataException("Invalid data format received from database");
            }

            String titleId = (String) row[0];
            String title = (String) row[1];
            String type = (String) row[2];

            if (row[3] == null) {
                throw new InvalidDataException("Author count is missing");
            }

            Long authorCount = ((Number) row[3]).longValue();
            if (row[4] == null) {
                throw new InvalidDataException("Author names are missing");
            }

            List<String> authorNames = Arrays.asList(((String) row[4]).split(", "));
            dtos.add(new MultiAuthorTitlesDTO(titleId, title, type, authorCount, authorNames));
        }

        return dtos;
    }
}