package com.cg.service;

import com.cg.dto.AuthorBookPublisherDto;

import java.util.List;

public interface IAuthorService {
    List<AuthorBookPublisherDto> getAuthorsWithBooksAndPublishers();
}
