package com.example.cloud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (unique = true,nullable = false)
    private Long id;
    @Column (nullable = false)
    private String username;
    @Column (nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    List<File> userFiles;


}
