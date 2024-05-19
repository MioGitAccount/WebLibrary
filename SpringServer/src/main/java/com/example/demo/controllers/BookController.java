package com.example.demo.controllers;

import com.example.demo.Model.Book;
import com.example.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("")
    public List<Book> findAll(){
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Book book){
        service.save(book);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void update(@RequestBody Book book,@PathVariable Integer id){
        service.update(book,id);

    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(){
        //to do
    }
}
