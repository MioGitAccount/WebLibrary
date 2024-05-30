package com.example.demo.controllers;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    //TO DO: RETURN ResponseEntity<String>
    @CrossOrigin
    @GetMapping("")
    public List<Book> findAll(){
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id){
        Book book = service.findById(id);
        book.setCoverPageImageName("/api/book/view/"+ book.getCoverPageImageName());
        return book;

    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestParam("title") String title,
                       @RequestParam("desc") String desc,
                       @RequestParam("author") String author,
                       @RequestParam("category") Category category,
                       @RequestParam("releaseYear") Integer releaseYear,
                       @RequestParam("image") MultipartFile imageFile
                       ){

        Book book = new Book();
        book.setTitle(title);
        book.setDecs(desc);
        book.setAuthor(author);
        book.setCategory(category);
        book.setReleaseYear(releaseYear);
        service.save(book,imageFile);
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
    @GetMapping("/view/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> viewImage(@PathVariable String filename){
        Resource resource = service.getImage(filename);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
