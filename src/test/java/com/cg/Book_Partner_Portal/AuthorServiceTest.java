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

    // API 1 (POSITIVE)
    @Test
    void testGetAuthorsWithBooksAndPublishers_Positive() {
        com.cg.entity.Authors author = new com.cg.entity.Authors();
        author.setAuId("A1");
        author.setFirstName("John");
        author.setLastName("Doe");

        when(authorRepo.findAll()).thenReturn(List.of(author));

        List<com.cg.dto.AuthorBookPublisherDTO> result = authorService.getAuthorsWithBooksAndPublishers();

        assertEquals(1, result.size());
        assertEquals("A1", result.get(0).getAuthorId());
        verify(authorRepo, times(1)).findAll();
    }

    // API 1 (NEGATIVE - Not Found)
    @Test
    void testGetAuthorsWithBooksAndPublishers_NotFoundException() {
        when(authorRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(com.cg.exception.ResourceNotFoundException.class, () -> {
            authorService.getAuthorsWithBooksAndPublishers();
        });
    }

    // API 1 (NEGATIVE - Bad Request)
    @Test
    void testGetAuthorsWithBooksAndPublishers_BadRequestException() {
        com.cg.entity.Authors author = new com.cg.entity.Authors();
        author.setAuId(null); // Will trigger BadRequestException

        when(authorRepo.findAll()).thenReturn(List.of(author));

        assertThrows(com.cg.exception.BadRequestException.class, () -> {
            authorService.getAuthorsWithBooksAndPublishers();
        });
    }

    // API 5 (POSITIVE)
    @Test
    void testBestSellingBooks_Positive() {

        List<BestSellingBookDTO> mockData = List.of(
                new BestSellingBookDTO("1", "John", "T1", "Book1", 100L, 500.0));

        verify(authorRepo, times(1)).findBestSellingBooks(); // verify mock called
    }

    // API 5 (NEGATIVE)
    @Test
    void testBestSellingBooks_Exception() {

        when(authorRepo.findBestSellingBooks()).thenReturn(Collections.emptyList());

        assertThrows(InvalidDataException.class, () -> {

        });
    }

    // API 6 (POSITIVE)
    @Test
    void testRoyaltyRange_Positive() {

        List<AuthorRoyaltyDTO> mockData = List.of(
                new AuthorRoyaltyDTO("1", "John", "T1", "Book1", 10, 20));

        when(authorRepo.findAuthorsWithRoyaltyRange()).thenReturn(Collections.emptyList());

        List<AuthorRoyaltyDTO> result = authorService.getAuthorsWithRoyaltyRange();

        assertEquals(1, result.size());

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