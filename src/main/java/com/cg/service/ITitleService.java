package com.cg.service;

import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.MultiAuthorTitlesDTO;
import com.cg.dto.TitleSalesByStoreDTO;

import java.util.List;


public interface ITitleService {

    List<TitleSalesByStoreDTO> getAllTitlesWithSalesByStore();

    List<AuthorTitlesUnderPriceDTO> getTitlesByMaxPrice(Double maxPrice);

    List<MultiAuthorTitlesDTO> getMultiAuthorTitles();
}
