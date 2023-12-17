package com.example.cloud.service;

import com.example.cloud.models.File;
import com.example.cloud.repository.FileRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor

public class FileService {
    private Map<String, File> fileMap = new ConcurrentHashMap<>();
    private final FileRepository fileRepository;

    public void putFile(String fileName) {
        // если название файла есть в мапе - то ошибка
        fileMap.put(fileName, fileRepository.findByFilename(fileName).get());
        java.io.File file = new java.io.File().
    }

    public void deleteFile(String fileName) {
        // если файла не существует - ошибка.
        fileMap.remove(fileName);
    }

    public File getFile(String fileName) {
        // поиск файла, если файл не обнаружен - ошибка
        return fileMap.get(fileName);
    }

    public List<File> getAllFiles() {
        return new ArrayList<>(fileMap.values()).stream().sorted().toList();
    }

    public void editFileName (String fileName, String newFileName){
    File file = fileMap.remove(fileName);
    file.setFilename(newFileName);
    fileMap.put(newFileName,file);


    }
}





