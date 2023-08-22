package com.example.testmanager.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "speciments", schema = "public")
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speicimen_id", updatable = false, nullable = false, unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "programm_id", nullable = false)
    private Program program;
    @Column(name = "marking", nullable = false)
    private String marking;
    @Enumerated(EnumType.STRING)
    @Column(name = "standard")
    private Standard standard;
    @Column(name = "protocol")
    private String protocol;

    public enum Standard {
        ASTMD3039,
        ASTMD6641,
        ASTMD2344,
        ASTMD7078
    }
}
