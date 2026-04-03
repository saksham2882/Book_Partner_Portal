package com.cg.Book_Partner_Portal;

import com.cg.dto.StorePerformanceDTO;
import com.cg.entity.Store;
import com.cg.exception.BadRequestException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repository.IStoreRepo;
import com.cg.service.impl.StoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    private IStoreRepo storeRepo;

    @InjectMocks
    private StoreServiceImpl storeService;

    // API 2 (POSITIVE)
    @Test
    void testGetStorePerformance_Positive() {
        Store store = new Store();
        store.setStoreId("S1");
        store.setStoreName("Book Store");
        
        when(storeRepo.findAll()).thenReturn(List.of(store));
        
        List<StorePerformanceDTO> result = storeService.getStorePerformance();
        
        assertEquals(1, result.size());
        assertEquals("S1", result.get(0).getStoreId());
        verify(storeRepo, times(1)).findAll();
    }

    // API 2 (NEGATIVE - Not Found)
    @Test
    void testGetStorePerformance_NotFoundException() {
        when(storeRepo.findAll()).thenReturn(Collections.emptyList());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            storeService.getStorePerformance();
        });
    }

    // API 2 (NEGATIVE - Bad Request)
    @Test
    void testGetStorePerformance_BadRequestException() {
        Store store = new Store();
        store.setStoreId(null); // Will trigger BadRequestException
        
        when(storeRepo.findAll()).thenReturn(List.of(store));
        
        assertThrows(BadRequestException.class, () -> {
            storeService.getStorePerformance();
        });
    }
}
