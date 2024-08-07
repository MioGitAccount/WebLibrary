package com.example.demo.controllers;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.CategoryName;
import com.example.demo.Services.BookService;
import com.example.demo.repository.Specification.BookSpecification;
import com.example.demo.repository.Specification.Utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    public Page<BookDTO> findAll(@RequestParam(required = false) String title,
                                 @RequestParam(required = false) String author,
                                 @RequestParam(required = false) Set<String> categories,
                                 @RequestParam(required = false) Integer releaseYear,
                                 @RequestParam(required = false) String publisher,
                                 @RequestParam(required = false) LocalDate dateAdded,
                                 @RequestParam(required = false) String language,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id,asc") String[] sort){
        Set<Category> categorySet = categories == null ? null :
                categories.stream().map(CategoryName::valueOf).map(cat ->service.getCategoryByName(cat)).collect(Collectors.toSet());
        Specification<Book> specification = Specification
                .where(BookSpecification.hasTitle(title))
                .and(BookSpecification.hasAuthor(author))
                .and(BookSpecification.hasCategory(categorySet))
                .and(BookSpecification.hasReleaseYear(releaseYear))
                .and(BookSpecification.hasPublisher(publisher))
                .and(BookSpecification.afterDate(dateAdded))
                .and(BookSpecification.hasLanguage(language));

        Sort sortOrder = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));
        Pageable pageable = PageRequest.of(page, size, sortOrder);


        return PaginationUtils.listToPage(service.findAll(specification,pageable).stream().map(book -> new BookDTO(book,"/api/book/view/","/api/book/pdf/")).collect(Collectors.toList()),pageable);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Integer id){
        return new BookDTO(service.findById(id),"/api/book/view/","/api/book/pdf/");

    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestParam("title") String title,
                       @RequestParam("description") String desc,
                       @RequestParam("author") String author,
                       @RequestParam("category") Set<String> categories,
                       @RequestParam("releaseYear") Integer releaseYear,
                       @RequestParam("publisher") String publisher,
                       @RequestParam("language") String language,
                       @RequestParam("image") MultipartFile imageFile,
                       @RequestParam("pdf") MultipartFile pdfFile
                       ){

        Set<Category> categorySet = categories.stream()
                .map(name -> service.getCategoryByName(CategoryName.valueOf(name)))
                .collect(Collectors.toSet());
        Book book = new Book();
        book.setTitle(title);
        book.setDesc(desc);
        book.setAuthor(author);
        book.setCategory(categorySet);
        book.setReleaseYear(releaseYear);
        book.setPublisher(publisher);
        book.setLanguage(language);
        book.setDateAdded(LocalDate.now());
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
    @CrossOrigin
    @GetMapping("/categories")
    @ResponseBody
    public Set<CategoryName> findAllCategories(){
        return service.getAllCategoryNames();
    }

}
