package com.example.testmanager.service;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.mappers.ProgramMapper;
import com.example.testmanager.repository.ProgramRepository;
import com.example.testmanager.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
