package com.cg.controller;

import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.TitleSalesByStoreDTO;
import com.cg.service.ITitleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    private final ITitleService titleService;

    public TitleController(ITitleService titleService) {
        this.titleService = titleService;
    }


    // API 2:
    @GetMapping("/sales-by-store")
    public ResponseEntity<List<TitleSalesByStoreDTO>> getTitlesWithSalesByStore() {
        List<TitleSalesByStoreDTO> response = titleService.getAllTitlesWithSalesByStore();
        return ResponseEntity.ok(response);
    }


    // API 3:
    @GetMapping("/filter")
    public ResponseEntity<List<AuthorTitlesUnderPriceDTO>> getBooksByMaxPrice(@RequestParam(name = "maxPrice", required = true) Double maxPrice) {
        List<AuthorTitlesUnderPriceDTO> result = titleService.getTitlesByMaxPrice(maxPrice);
        return ResponseEntity.ok(result);
    }
}