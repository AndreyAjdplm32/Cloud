package com.example.cloud.controller;

import com.example.cloud.models.File;
import com.example.cloud.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/cloud")

public class CloudController {
    public final FileStorageService fileStorageService;

    public CloudController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;

    }


    @PostMapping("/file")

    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile multipartfile) throws IOException {
        String response = fileStorageService.putFile(multipartfile);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get")

    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        byte[] imageData = fileStorageService.getFile(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    @PostMapping("/delete")
    public void deleteFile(String fileName) throws Exception {
        fileStorageService.deleteFile(fileName);
    }

    @GetMapping("/list")
    public List<File> fileList() {
        return fileStorageService.getAllFiles();
    }

    @PostMapping("/put")
    public void edditFileName(String fileName, String newFileName) {
        fileStorageService.editFileName(fileName, newFileName);
    }


}

