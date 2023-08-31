package com.example.testmanager.controller;

import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.dto.SpecimenDtoUpd;
import com.example.testmanager.service.SpecimenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping
public class SpecimenController {
    private final SpecimenService specimenService;

    @PostMapping(path = "/specimens")
    public ResponseEntity<Object> createSpecimen(@RequestBody NewSpecimenDto newSpecimen) {
        log.info("Внесение в базу образца № " + newSpecimen.getMarking());
        return new ResponseEntity<>(specimenService.createSpecimen(newSpecimen), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/specimens/{specimenId}")
    public ResponseEntity<Object> patch(@Positive @PathVariable Long specimenId, @RequestBody SpecimenDtoUpd specimenDtoUpd) {
        log.info("Обновление данных образца {}", specimenId);
        return new ResponseEntity<>(specimenService.update(specimenId, specimenDtoUpd), HttpStatus.CREATED);
    }
}
