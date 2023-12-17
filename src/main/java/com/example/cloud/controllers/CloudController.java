package com.example.cloud.controllers;

import com.example.cloud.models.File;
import com.example.cloud.models.JwtRequest;
import com.example.cloud.service.AuthService;
import com.example.cloud.service.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@RestController
@RequiredArgsConstructor
public class CloudController {


    private final FileStorageService fileStorageService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final JwtRequest loginRequest) throws AuthException, JsonProcessingException {
        authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @PostMapping("/file")
    public ResponseEntity<?> saveFile(@RequestBody MultipartFile multipartfile){
         fileStorageService.putFile(multipartfile);
         return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get")
    public ResponseEntity<byte[]> get(@PathVariable String fileName) {
        return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).body(fileStorageService.getFile(fileName));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<File>> fileList() {
        List<File>list = fileStorageService.getAllFiles();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/put")
    public ResponseEntity <File> editFileName(@RequestParam("filename") String fileName, String newFileName) {
        fileStorageService.editFileName(fileName, newFileName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}

