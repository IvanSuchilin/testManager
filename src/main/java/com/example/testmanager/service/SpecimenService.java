package com.example.testmanager.service;

import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.dto.SpecimenDtoUpd;
import com.example.testmanager.exceptions.DataAlreadyExistException;
import com.example.testmanager.exceptions.NotFounElementException;
import com.example.testmanager.mappers.SpecimenMapper;
import com.example.testmanager.model.Specimen;
import com.example.testmanager.repository.SpecimenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecimenService {
    //private final DtoValidator validator;
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    public Object createSpecimen(NewSpecimenDto newSpecimen) {
        //validator.validateSpecimenDto(newSpecimen);
        log.debug("Получен запрос на сохранение данных по образцу {}", newSpecimen.getMarking());
        if (specimenRepository.findAll()
                .stream()
                .anyMatch(u -> u.getMarking().equals(newSpecimen.getMarking()) && u.getProgram().equals(newSpecimen.getProgram()))) {
            throw new DataAlreadyExistException("Данные по этому образцу уже внесены", "Необходимо редактировать данные",
                    LocalDateTime.now());
        }
        return specimenMapper.INSTANCE.toSpecimenDto(specimenRepository.save(specimenMapper.INSTANCE.toSpecimen(newSpecimen)));
    }

    public Object update(Long specimenId, SpecimenDtoUpd specimenDtoUpd) {
        Specimen stored = specimenRepository.findById(specimenId).orElseThrow(() -> new NotFounElementException("Образец с id" + specimenId +
                "не найден", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        log.debug("Получен запрос на обновление данных по образцу {}", stored.getMarking());
        return specimenRepository.save(stored);
    }
}
