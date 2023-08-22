package com.example.testmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "programms")
public class Programm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speicimen_id", updatable = false, nullable = false, unique = true)
    private Long id;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "annotation", nullable = false)
    private String annotation;
}
