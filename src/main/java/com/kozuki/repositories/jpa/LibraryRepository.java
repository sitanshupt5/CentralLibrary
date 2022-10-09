package com.kozuki.repositories.jpa;

import com.kozuki.beans.CentralLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<CentralLibrary,String>, LibraryRepositoryCustom {

    public CentralLibrary getBookById(String id);
}
