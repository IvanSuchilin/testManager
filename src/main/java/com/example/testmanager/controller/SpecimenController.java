package com.example.testmanager.controller;

import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.service.SpecimenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/specimen")
public class SpecimenController {
    private final SpecimenService specimenService;

    @PostMapping
    public ResponseEntity<Object> createSpecimen(@RequestBody NewSpecimenDto newSpecimen){
        log.info("Внесение в базу образца №{}", newSpecimen.getMarking());
        return new ResponseEntity<>(specimenService.createSpecimen(newSpecimen), HttpStatus.CREATED);
    }
}
