package com.example.testmanager.service;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.exceptions.NotFounElementException;
import com.example.testmanager.mappers.ProgramMapper;
import com.example.testmanager.model.Program;
import com.example.testmanager.model.Specimen;
import com.example.testmanager.repository.ProgramRepository;
import com.example.testmanager.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramMapper programMapper;
    private final ProgramRepository programRepository;
    private final Validator validator;

    public Object createProgram(NewProgramDto newProgramDto) {
        validator.validateNewProgramDto(newProgramDto);
        return programRepository.save(programMapper.toProgram(newProgramDto));
    }

    public List<Program> getPrograms(){
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
}
