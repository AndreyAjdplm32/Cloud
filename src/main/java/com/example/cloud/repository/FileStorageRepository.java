package com.example.cloud.repository;

import com.example.cloud.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileStorageRepository extends JpaRepository<File,Long> {
  Optional <File> findByFileName (String name);


}
