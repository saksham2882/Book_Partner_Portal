package com.cg.Book_Partner_Portal;

import com.cg.dto.AuthorTitlesUnderPriceDTO;
import com.cg.dto.MultiAuthorTitlesDTO;
import com.cg.dto.TitleSalesByStoreDTO;
import com.cg.dto.TitleSalesResponseDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repository.ITitleRepo;
import com.cg.service.impl.TitleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TitleServiceTest {

    @Mock
    private ITitleRepo titleRepository;

    @InjectMocks
    private TitleServiceImpl titleService;


    // API 2: getAllTitlesWithSalesByStore - POSITIVE
    @Test
    void testGetAllTitlesWithSalesByStore_Positive() {
        TitleSalesByStoreDTO d1 = new TitleSalesByStoreDTO("T1", "Java", "Coding", 20.0, "S1", "Store1", "Noida", (short) 10);
        TitleSalesByStoreDTO d2 = new TitleSalesByStoreDTO("T1", "Java", "Coding", 20.0, "S2", "Store2", "Delhi", (short) 5);

        when(titleRepository.findAllTitlesWithSalesByStore()).thenReturn(List.of(d1, d2));

        List<TitleSalesResponseDTO> result = titleService.getAllTitlesWithSalesByStore();

        assertNotNull(result);
        assertEquals(1, result.size());

        TitleSalesResponseDTO response = result.get(0);

        assertEquals("T1", response.getTitleId());
        assertEquals(2, response.getSaleInfo().size());

        verify(titleRepository, times(1)).findAllTitlesWithSalesByStore();
    }


    // API 2: getAllTitlesWithSalesByStore - NEGATIVE (Not Found)
    @Test
    void testGetAllTitlesWithSalesByStore_NotFoundException() {
        when(titleRepository.findAllTitlesWithSalesByStore()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            titleService.getAllTitlesWithSalesByStore();
        });
    }


    // API 3: getTitlesByMaxPrice - POSITIVE
    @Test
    void testGetTitlesByMaxPrice_Positive() {
        AuthorTitlesUnderPriceDTO dto = new AuthorTitlesUnderPriceDTO("T1", "Python", "Coding", 15.5, 10, 1000, "A1", "Harry", (byte) 1, 50);

        when(titleRepository.findTitlesUnderPrice(20.0)).thenReturn(List.of(dto));

        List<AuthorTitlesUnderPriceDTO> result = titleService.getTitlesUnderPrice(20.0);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("T1", result.get(0).getTitleId());
        verify(titleRepository, times(1)).findTitlesUnderPrice(20.0);
    }


    // API 3: getTitlesByMaxPrice - NEGATIVE (Negative Price)
    @Test
    void testGetTitlesByMaxPrice_NegativePrice_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            titleService.getTitlesUnderPrice(-5.0);
        });
    }


    // API 3: getTitlesByMaxPrice - NEGATIVE (No books found)
    @Test
    void testGetTitlesByMaxPrice_NotFoundException() {
        when(titleRepository.findTitlesUnderPrice(10.0)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            titleService.getTitlesUnderPrice(10.0);
        });
    }


    // API 9:
    @Test
    void testGetMultiAuthorTitles_Success() {

        List<Object[]> mockData = new ArrayList<>();
        mockData.add(new Object[]{"T1", "Java Basics", "Tech", 2, "Author1, Author2"});

        when(titleRepository.findMultiAuthorTitles()).thenReturn(mockData);

        List<MultiAuthorTitlesDTO> result = titleService.getMultiAuthorTitles();

        assertNotNull(result);
        assertEquals(1, result.size());
    }


    // API 9:
    @Test
    void testGetMultiAuthorTitles_NoData() {
        when(titleRepository.findMultiAuthorTitles()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> {
            titleService.getMultiAuthorTitles();
        });
    }
}
