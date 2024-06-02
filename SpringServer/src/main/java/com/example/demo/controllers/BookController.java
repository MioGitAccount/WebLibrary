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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<Book> listOfBooks = service.findAll();
        for(Book book : listOfBooks) {
            book.setCoverPageImageName("/api/book/view/" + book.getCoverPageImageName());
            book.setPdfFileName("/api/book/pdf/" + book.getPdfFileName());
        }
        return listOfBooks;


    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id){
        Book book = service.findById(id);
        book.setCoverPageImageName("/api/book/view/"+ book.getCoverPageImageName());
        book.setPdfFileName("/api/book/pdf/"+ book.getPdfFileName());
        return book;

    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestParam("title") String title,
                       @RequestParam("description") String desc,
                       @RequestParam("author") String author,
                       @RequestParam("category") String category,
                       @RequestParam("releaseYear") Integer releaseYear,
                       @RequestParam("publisher") String publisher,
                       @RequestParam("image") MultipartFile imageFile,
                       @RequestParam("pdf") MultipartFile pdfFile
                       ){

        Set<Category> categorySet = Arrays.stream(category.split(","))
                .map(Category::valueOf)
                .collect(Collectors.toSet());

        Book book = new Book();
        book.setTitle(title);
        book.setDesc(desc);
        book.setAuthor(author);
        book.setCategory(categorySet);
        book.setReleaseYear(releaseYear);
        book.setPublisher(publisher);
        service.save(book,imageFile,pdfFile);
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
    @CrossOrigin
    @GetMapping("/view/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> viewImage(@PathVariable String filename){
        Resource resource = service.getImage(filename);

        if(resource == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @CrossOrigin
    @GetMapping("/pdf/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> viewPdf(@PathVariable String filename){
        Resource resource = service.getPdf(filename);

        if(resource == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
