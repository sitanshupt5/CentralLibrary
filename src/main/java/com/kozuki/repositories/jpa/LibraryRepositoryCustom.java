package com.kozuki.repositories.jpa;

import com.kozuki.beans.CentralLibrary;

import java.util.List;

public interface LibraryRepositoryCustom {

    public List<CentralLibrary> findByAuthor(String author);


}
