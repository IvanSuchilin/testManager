package com.example.testmanager.repository;

import com.example.testmanager.model.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenRepository extends JpaRepository <Specimen, Long> {
}
