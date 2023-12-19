package com.example.cloud.service;

import com.example.cloud.exceptions.RequestErrors;
import com.example.cloud.exceptions.ServerErrors;
import com.example.cloud.models.File;
import com.example.cloud.repository.FileStorageRepository;


import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageRepository fileStorageRepository;

    @Transactional
    public void putFile(MultipartFile multipartFile) {
        try {
            fileStorageRepository.save(File.builder().
                    fileName(multipartFile.getOriginalFilename()).
                    fileType(multipartFile.getContentType()).
                    fileSize(multipartFile.getBytes()).
                    build());
            log.info("Файл загружен");
        } catch (IOException | RuntimeException e) {
            throw new RequestErrors(e.getMessage());
        }

    }

    @Transactional
    public void deleteFile(String fileName) throws RequestErrors{
            File file = fileStorageRepository.findByFileName(fileName).orElseThrow(() -> new RequestErrors("Такого файла не существует"));
            fileStorageRepository.delete(file);
            log.info("Файл удален");
    }

    @Transactional
    public byte[] getFile(String fileName) throws RequestErrors {
        File file = fileStorageRepository.findByFileName(fileName).orElseThrow(() -> new RequestErrors("Такого файла не существует"));
        log.info("Файл получен");
        return file.getFileSize();

    }

    @Transactional
    public List <File> getAllFiles() throws RequestErrors {
        List<File> files = fileStorageRepository.findAll();
            if (files.isEmpty()) {
               throw new RequestErrors("Список пуст");
            } else {
                log.info("Cписок получен");
                return List.copyOf(files);
            }
    }

    @Transactional
    public void editFileName(String fileName, String newFileName) throws RequestErrors {
            File file = fileStorageRepository.findByFileName(fileName).orElseThrow(() -> new RequestErrors("Такого файла не существует"));
            file.setFileName(newFileName);
            fileStorageRepository.save(file);
            log.info("Название файла изменено");
    }


}





