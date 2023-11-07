package com.example.testmanager.service;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.exceptions.DataAlreadyExistException;
import com.example.testmanager.exceptions.NotFounElementException;
import com.example.testmanager.mappers.ProgramMapper;
import com.example.testmanager.model.Program;
import com.example.testmanager.repository.ProgramRepository;
import com.example.testmanager.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramMapper programMapper;
    private final ProgramRepository programRepository;
    private final Validator validator;

    public Object createProgram(NewProgramDto newProgramDto) {
        validator.validateNewProgramDto(newProgramDto);
        if (programRepository.findAll()
                .stream()
                .anyMatch(u -> u.getAnnotation().equals(newProgramDto.getAnnotation()) && u.getNumber().equals(newProgramDto.getNumber()))) {
            throw new DataAlreadyExistException("Данные по этой программе уже внесены", "Необходимо редактировать данные ввода",
                    LocalDateTime.now());
        }
        return programRepository.save(programMapper.toProgram(newProgramDto));
    }

    public List<Program> getPrograms() {
        log.info("Получен запрос на получение данных по всем программам");
        return programRepository.findAll();
    }

    public void deleteProgram(Long programId) {
        Program stored = programRepository.findById(programId).orElseThrow(() -> new NotFounElementException("Программа с id" + programId +
                "не найдена", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        log.debug("Получен запрос на удаление данных программы {}", stored.getNumber());
        programRepository.deleteById(programId);
        log.info("Данные по программе {} удалены", stored.getNumber());
    }

    public Object updateProgram(Long programId, Program program) {
        Program stored = programRepository.findById(programId).orElseThrow(() -> new NotFounElementException("Программа с id" + programId +
                "не найдена", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        log.info("Получен запрос на обновление данных по программе", stored.getNumber());
        Program updProgram = updateProgram(program, stored);
        return programRepository.save(updProgram);
    }

    private Program updateProgram(Program updProgram, Program stored) {
        List<Program> programs = programRepository.findAll();
        if (updProgram == null) {
            return stored;
        }
        if (updProgram.getNumber() != null && !updProgram.getNumber().equals("")) {
            if (programs
                    .stream()
                    .anyMatch(u -> u.getNumber().equals(updProgram.getNumber()) && u.getAnnotation().equals(stored.getAnnotation()))) {
                throw new DataAlreadyExistException("Данные по этой программе уже внесены", "Необходимо редактировать данные ввода",
                        LocalDateTime.now());
            }
            stored.setNumber(updProgram.getNumber());
        }
        if (updProgram.getAnnotation() != null && !updProgram.getAnnotation().equals("")) {
            if (programs
                    .stream()
                    .anyMatch(u -> u.getAnnotation().equals(updProgram.getAnnotation()) && u.getNumber().equals(stored.getNumber()))) {
                throw new DataAlreadyExistException("Данные по этой программе уже внесены", "Необходимо редактировать данные ввода",
                        LocalDateTime.now());
            }
            stored.setAnnotation(updProgram.getAnnotation());
        }
        return stored;
    }
}
