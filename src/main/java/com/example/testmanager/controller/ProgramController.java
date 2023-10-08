package com.example.testmanager.controller;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.model.Program;
import com.example.testmanager.repository.ProgramRepository;
import com.example.testmanager.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ProgramController {
    private final ProgramService programService;
    private final ProgramRepository programRepository;
    @PostMapping(path = "/program")
    public ResponseEntity<Object> createProgram (@RequestBody NewProgramDto newProgramDto){
        log.info("Внесение данных программы {}", newProgramDto.getNumber());
        return new ResponseEntity<>(programService.createProgram(newProgramDto), HttpStatus.CREATED);
    }
    @GetMapping(path ="/program")
    public List<Program> getPrograms(){

        log.info("получение всех програм");
        return programRepository.findAll();
    }
}
