package com.example.demo03.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class LogImport {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;
    private Timestamp createDate;
    private Boolean status;
    private String fileName;

}
