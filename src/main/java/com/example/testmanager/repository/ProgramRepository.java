package com.example.testmanager.repository;

import com.example.testmanager.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository <Program, Long> {
}
