package com.example.demo.Services;

import com.example.demo.Model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    @Value("${upload.dir.img}")
    private String uploadDirImg;
    @Value("${upload.dir.pdf}")
    private String uploadDirPdf;

    private final BookRepository bookRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ImageRepository imageRepository) {
        this.bookRepository = bookRepository;
        this.imageRepository = imageRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found"));
    }

    public void save(Book book, MultipartFile imageFile, MultipartFile pdfFile){
        if (imageFile != null && !imageFile.isEmpty()){
            imageRepository.saveImage(imageFile,uploadDirImg);
            book.setCoverPageImageName(imageFile.getOriginalFilename());
        }
        if (pdfFile != null && !pdfFile.isEmpty()){
            imageRepository.saveImage(pdfFile,uploadDirPdf);
            book.setPdfFileName(pdfFile.getOriginalFilename());
            book.setBookSize(100);
        }

        bookRepository.save(book);
    }
    public void update(Book book, Integer id){
        Book oldBook = bookRepository.findById(id).orElse(null);
        if(oldBook == null)
            return ;
        bookRepository.save(book);
    }

    public Resource getImage(String filename) {
        return imageRepository.getImage(filename, uploadDirImg);
    }
    public Resource getPdf(String filename) {
        return imageRepository.getImage(filename, uploadDirPdf);
    }
}
