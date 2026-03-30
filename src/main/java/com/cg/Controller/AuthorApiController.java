package com.cg.Controller;


import com.cg.Service.IAuthorService;
import com.cg.dto.AuthorBookPublisherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorApiController {
    private final IAuthorService authorService;

    public AuthorApiController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/books-with-publishers")
    public ResponseEntity<List<AuthorBookPublisherDto>> getBooksWithPublishers() {
        return ResponseEntity.ok(authorService.getAuthorsWithBooksAndPublishers());
    }
}
