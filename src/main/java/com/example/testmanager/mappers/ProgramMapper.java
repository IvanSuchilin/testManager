package com.example.testmanager.mappers;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.model.Program;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    Program toProgram(NewProgramDto newProgramDto);
    NewProgramDto toProgramDto(Program program);
}
