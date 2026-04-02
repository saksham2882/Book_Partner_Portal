package com.cg.Book_Partner_Portal;
import com.cg.service.impl.AuthorServiceImpl;
import com.cg.dto.AuthorRoyaltyDTO;
import com.cg.dto.BestSellingBookDTO;
import com.cg.exception.InvalidDataException;
import com.cg.repository.IAuthorRepo;
import com.cg.service.impl.AuthorServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private IAuthorRepo authorRepo;

    @InjectMocks
    private AuthorServiceImpl authorService;
    // API 5 (POSITIVE)
    @Test
    void testBestSellingBooks_Positive() {

        List<BestSellingBookDTO> mockData = List.of(
                new BestSellingBookDTO("1", "John", "T1", "Book1", 100L, 500.0)
        );

        when(authorRepo.findBestSellingBooks()).thenReturn(mockData);

        List<BestSellingBookDTO> result = authorService.getBestSellingBooks();

        assertEquals(1, result.size());
        verify(authorRepo, times(1)).findBestSellingBooks(); // verify mock called
    }
    // API 5 (NEGATIVE)
    @Test
    void testBestSellingBooks_Exception() {

        when(authorRepo.findBestSellingBooks()).thenReturn(Collections.emptyList());

        assertThrows(InvalidDataException.class, () -> {
            authorService.getBestSellingBooks();
        });
    }

    // API 6 (POSITIVE)
    @Test
    void testRoyaltyRange_Positive() {

        List<AuthorRoyaltyDTO> mockData = List.of(
                new AuthorRoyaltyDTO("1", "John", "T1", "Book1", 10, 20)
        );

        when(authorRepo.findAuthorsWithRoyaltyRange()).thenReturn(mockData);

        List<AuthorRoyaltyDTO> result = authorService.getAuthorsWithRoyaltyRange();

        assertEquals(1, result.size());
        verify(authorRepo, times(1)).findAuthorsWithRoyaltyRange();
    }

    // API 6 (NEGATIVE)
    @Test
    void testRoyaltyRange_Exception() {

        List<AuthorRoyaltyDTO> mockData = List.of(
                new AuthorRoyaltyDTO("1", "John", "T1", "Book1", 30, 10) // invalid
        );

        when(authorRepo.findAuthorsWithRoyaltyRange()).thenReturn(mockData);

        assertThrows(InvalidDataException.class, () -> {
            authorService.getAuthorsWithRoyaltyRange();
        });
    }
}