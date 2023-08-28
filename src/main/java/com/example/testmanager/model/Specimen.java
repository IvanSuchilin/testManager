package com.example.testmanager.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "specimens", schema = "public")
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specimen_id", updatable = false, nullable = false, unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
    @Column(name = "marking", nullable = false)
    private String marking;
    //@Enumerated(EnumType.STRING)
    @Column(name = "test_standard", nullable = false)
    private String standard;
    @Column(name = "protocol")
    private String protocol;
    @Column(name = "strength")
    private Double strength;
    @Column(name = "module")
    private Double module;

   /* public enum Standard {
        ASTMD3039,
        ASTMD6641,
        ASTMD2344,
        ASTMD7078
    }*/
}
