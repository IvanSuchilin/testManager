package com.example.testmanager.mappers;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.dto.SpecimenDtoUpd;
import com.example.testmanager.model.Program;
import com.example.testmanager.model.Specimen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    Program toProgram(NewProgramDto newProgramDto);
    NewProgramDto toProgramDto(Program program);

    @Mapping(target = "id", ignore = true)
    Program updateProgram(Program updProgram, @MappingTarget Program stored);
}
