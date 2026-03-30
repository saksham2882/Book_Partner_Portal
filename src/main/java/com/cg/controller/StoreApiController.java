package com.cg.controller;

import com.cg.dto.StorePerformanceDTO;
import com.cg.service.IStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreApiController {

    private final IStoreService storeService;

    public StoreApiController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/performance")
    public ResponseEntity<List<StorePerformanceDTO>> getStorePerformance() {
        return ResponseEntity.ok(storeService.getStorePerformance());
    }
}