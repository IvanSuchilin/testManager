package com.example.testmanager.mappers;

import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.model.Specimen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
                ProgramMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpecimenMapper {
    SpecimenMapper INSTANCE = Mappers.getMapper(SpecimenMapper.class);
    @Mapping(target = "program.id", source = "program")
    Specimen toSpecimen(NewSpecimenDto newSpecimenDto);

    @Mapping(target = "program", source = "program.id")
    NewSpecimenDto toSpecimenDto(Specimen save);
}
