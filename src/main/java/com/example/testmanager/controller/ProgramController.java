package com.example.testmanager.controller;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.model.Program;
import com.example.testmanager.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ProgramController {
    private final ProgramService programService;

    @PostMapping(path = "/program")
    public ResponseEntity<Object> createProgram(@RequestBody NewProgramDto newProgramDto) {
        log.info("Внесение данных программы {}", newProgramDto.getNumber());
        return new ResponseEntity<>(programService.createProgram(newProgramDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/program")
    public ResponseEntity<Object> getPrograms() {
        log.info("получение всех програм");
        return new ResponseEntity<>(programService.getPrograms(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/program/{programId}")
    public ResponseEntity<Object> deleteProgram(@Positive @PathVariable Long programId) {
        log.info("Удаление данных программы {}", programId);
        programService.deleteProgram(programId);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/program/{programId}")
    public ResponseEntity<Object> patchProgram(@Positive @PathVariable Long programId, @RequestBody Program program) {
        log.info("Обновление данных программы {}", programId);
        return new ResponseEntity<>(programService.updateProgram(programId, program), HttpStatus.CREATED);
    }

}
