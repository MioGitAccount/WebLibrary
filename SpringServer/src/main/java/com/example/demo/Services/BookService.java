package com.example.demo.Services;

import com.example.demo.Model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found"));
    }

    public void save(Book book){
        bookRepository.save(book);
    }
    public void update(Book book, Integer id){
        Book oldBook = bookRepository.findById(id).orElse(null);
        if(oldBook == null)
            return ;
        bookRepository.save(book);
    }

}
