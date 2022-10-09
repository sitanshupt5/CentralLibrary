package com.kozuki.controllers;

import com.kozuki.beans.AddBookResponse;
import com.kozuki.beans.CentralLibrary;
import com.kozuki.repositories.jpa.LibraryRepository;
import com.kozuki.repositories.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CentralLibraryController {
    @Autowired
    LibraryRepository repository;
    @Autowired
    LibraryService service;

    @PostMapping("/addBook")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody CentralLibrary library)
    {
        AddBookResponse addBookResponse = new AddBookResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.add("correlationId", service.buildCorrelationId());
        if (!service.checkBookExists(library.getBook_name()))
        {
            library.setIsbn(service.buildIsbn());
            library.setAisle(service.selectAisle());
            library.setId(service.buildId());
            repository.save(library);
            addBookResponse.setMessage("Book Details have been successfully added");
            addBookResponse.setId(library.getId());
            return new ResponseEntity<AddBookResponse>(addBookResponse, headers, HttpStatus.CREATED);
        }
        else{
            addBookResponse.setMessage("Book already present in the library");
            addBookResponse.setId(service.getExistingBookId(library.getBook_name()));
            return new ResponseEntity<AddBookResponse>(addBookResponse, headers, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/getBooks")
    public List<CentralLibrary> getAllBooks()
    {
        return repository.findAll();
    }

    @GetMapping("/getBook")
    public CentralLibrary getBookByTitle(@RequestParam(name = "book_name", required = false)String book_name){
        try{
            return repository.findById(book_name).get();
        }catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    /*@GetMapping("/getBook")
    public List<CentralLibrary> getBooksByAuthor(@RequestParam(name = "author")String author){
        return repository.findByAuthor(author);
    }*/

    @GetMapping("/getBook/{id}")
    public ResponseEntity<CentralLibrary> getBookById(@PathVariable(value = "id")String id){
        return new ResponseEntity<CentralLibrary>(repository.getBookById(id), HttpStatus.OK);
    }

    @PutMapping("updateBook/{id}")
    public CentralLibrary updateBookById(@PathVariable(value = "id")String id, @RequestBody CentralLibrary library){
        CentralLibrary book = repository.getBookById(id);
        if(!(book.getBook_name()==null)||!(book.getBook_name().isEmpty()))
        {
            if(!(library.getAisle()==0))
                book.setAisle(library.getAisle());
            if (!library.getAuthor().isEmpty())
                book.setAuthor(library.getAuthor());
            if (!library.getIsbn().isEmpty())
                book.setIsbn(library.getIsbn());
        }
        repository.save(book);
        return repository.getBookById(id);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBookById(@RequestBody CentralLibrary library){
        repository.delete(repository.getBookById(library.getId()));
        return new ResponseEntity<String>("Book is deleted", HttpStatus.ACCEPTED);
    }

}
