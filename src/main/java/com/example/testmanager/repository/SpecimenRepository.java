package com.example.testmanager.repository;

import org.jetbrains.annotations.NotNull;
import com.example.testmanager.model.Specimen;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecimenRepository extends JpaRepository <Specimen, Long>, QuerydslPredicateExecutor<Specimen> {
    Page<Specimen> findAll(@NotNull Predicate value, @NotNull Pageable pageable);
}
