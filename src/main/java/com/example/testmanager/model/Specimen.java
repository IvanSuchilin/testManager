package com.example.testmanager.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "speciments")
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speicimen_id", updatable = false, nullable = false, unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "programm_id", nullable = false)
    private Programm programm;
    @Column(name = "marking", nullable = false)
    private String marking;
    @Enumerated(EnumType.STRING)
    @Column(name = "standart")
    private Standart standart;

    public enum Standart {
        ASTMD3039,
        ASTMD6641,
        ASTMD2344,
        ASTMD7078
    }
}
