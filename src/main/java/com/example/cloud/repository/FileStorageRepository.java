package com.example.cloud.repository;

import com.example.cloud.models.File;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FileRepository extends CrudRepository<File,Long> {
    Optional<File>findByFilename(String name);
    Optional<File>deleteByFilename(String name);


}
