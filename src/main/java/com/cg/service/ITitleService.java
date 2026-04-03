package com.cg.service;

import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.MultiAuthorTitlesDTO;
import com.cg.dto.TitleSalesResponseDTO;

import java.util.List;


public interface ITitleService {

    List<TitleSalesResponseDTO> getAllTitlesWithSalesByStore();

    List<AuthorTitlesUnderPriceDTO> getTitlesUnderPrice(Double maxPrice);

    List<MultiAuthorTitlesDTO> getMultiAuthorTitles();
}
