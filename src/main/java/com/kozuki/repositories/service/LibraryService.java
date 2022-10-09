package com.kozuki.repositories.service;

import com.github.javafaker.Faker;
import com.kozuki.repositories.jpa.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository rep;
    Faker fake = new Faker();

    public String buildIsbn()
    {
        return fake.random().hex(8);
    }
    public int selectAisle(){
        return fake.random().nextInt(100, 1000);
    }
    public String buildId()
    {
        return buildIsbn()+ selectAisle();
    }

    public String buildCorrelationId()
    {
        return fake.random().hex(30);
    }

    public Boolean checkBookExists(String bookName)
    {
        return rep.findById(bookName).isPresent();
    }

    public String getExistingBookId(String bookName)
    {
        return rep.findById(bookName).get().getId();
    }
}
