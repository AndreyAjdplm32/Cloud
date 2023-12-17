package com.example.cloud.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table (name = "files")

public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (nullable = false,unique = true)
    private Long id;
    @Column (nullable = false)
    private String fileName;
    @Column (nullable = false)
    private String fileType;
    @Column (nullable = false)
    private String filePath;
    @Column (nullable = false)
    private byte [] fileSize;

    @ManyToOne
    private User user;


}
