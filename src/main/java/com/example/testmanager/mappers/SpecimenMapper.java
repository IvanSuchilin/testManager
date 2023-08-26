package com.example.testmanager.mappers;

import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.model.Specimen;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpecimenMapper {
    SpecimenMapper INSTANCE = Mappers.getMapper(SpecimenMapper.class);
    Specimen toSpecimen(NewSpecimenDto newSpecimenDto);

    NewSpecimenDto toSpecimenDto(Specimen save);
}
