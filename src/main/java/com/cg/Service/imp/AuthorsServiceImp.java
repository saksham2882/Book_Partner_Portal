package com.cg.Service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.repository.IAuthorsRepo;
import com.cg.Service.AuthorsService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorsServiceImp implements AuthorsService{
    @Autowired
    private IAuthorsRepo AuthorsRepo;

    @Override
    public AuthorsDto saveAuthors(AuthorsDto dto) {
        return dto;
    }

}
