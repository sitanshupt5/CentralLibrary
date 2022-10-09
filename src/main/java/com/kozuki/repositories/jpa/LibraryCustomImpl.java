package com.kozuki.repositories.jpa;

import com.kozuki.beans.CentralLibrary;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LibraryCustomImpl implements LibraryRepositoryCustom{
    @Autowired
    LibraryRepository lib;

    @Override
    public List<CentralLibrary> findByAuthor(String author) {
        List<CentralLibrary> books = lib.findAll();
        List<CentralLibrary> booksByAuthor = new ArrayList<>();
        for (CentralLibrary book:books){
            if (book.getAuthor().equalsIgnoreCase(author))
                booksByAuthor.add(book);
        }
        return booksByAuthor;
    }
}
