package com.example.testmanager.controller;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ProgramController {
    private final ProgramService programService;
    @PostMapping(path = "/program")
    public ResponseEntity<Object> createProgram (@RequestBody NewProgramDto newProgramDto){
        log.info("Внесение данных программы {}", newProgramDto.getNumber());
        return new ResponseEntity<>(programService.createProgram(newProgramDto), HttpStatus.CREATED);
    }
}
