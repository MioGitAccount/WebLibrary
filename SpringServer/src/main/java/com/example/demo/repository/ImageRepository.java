package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class ImageRepository {

    public ImageRepository(){}

    public String saveImage(MultipartFile imageFile,String uploadDir){
        String fileName = imageFile.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);

        Path path = Paths.get(uploadDir +"/" + fileName);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(imageFile.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;

    }

    public Resource getImage(String filename, String uploadDir) {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
