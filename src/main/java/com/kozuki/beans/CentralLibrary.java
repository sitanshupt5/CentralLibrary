package com.kozuki.beans;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "Library")
public class CentralLibrary {

    @Id
    @Column(name = "book_name")
    private String book_name;
    @Column(name = "id")
    private String id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "aisle")
    private int aisle;
    @Column(name = "author")
    private String author;


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String bookName) {
        this.book_name = bookName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



}
