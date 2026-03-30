package com.cg.service.Impl;

import com.cg.service.IAuthorService;
import com.cg.dto.AuthorBookPublisherDto;
import com.cg.repository.IAuthorRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements IAuthorService {
    private final IAuthorRepo authorRepository;
    public AuthorServiceImpl(IAuthorRepo authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorBookPublisherDto> getAuthorsWithBooksAndPublishers() {
        return authorRepository.findAll().stream().map(author -> {
            AuthorBookPublisherDto dto = new AuthorBookPublisherDto();
            dto.setAuthorId(author.getAuId());
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorLastName(author.getLastName());

            List<AuthorBookPublisherDto.BookPublisherDetail> books = new ArrayList<>();
            if (author.getTitleAuthors() != null) {
                for (TitleAuthor ta : author.getTitleAuthors()) {
                    if (ta.getTitle() != null) {
                        Title title = ta.getTitle();
                        String pubId = title.getPublisher() != null ? title.getPublisher().getPubId() : null;
                        String pubName = title.getPublisher() != null ? title.getPublisher().getName() : null;
                        books.add(new AuthorBookPublisherDto.BookPublisherDetail(
                                title.getTitleId(), title.getTitle(), pubId, pubName
                        ));
                    }
                }
            }
            dto.setBooks(books);
            return dto;
        }).collect(Collectors.toList());
    }

}
