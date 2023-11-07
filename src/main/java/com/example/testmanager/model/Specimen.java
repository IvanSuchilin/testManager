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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Program program;
    @Column(name = "marking", nullable = false)
    private String marking;
    @Column(name = "standard", nullable = false)
    private String standard;
    @Column(name = "protocol")
    private String protocol;
    @Column(name = "strength")
    private Double strength;
    @Column(name = "module")
    private Double module;
}
