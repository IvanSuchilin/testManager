package com.example.testmanager.service;

import com.example.testmanager.model.QSpecimen;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.dto.SpecimenDto;
import com.example.testmanager.dto.SpecimenDtoUpd;
import com.example.testmanager.exceptions.DataAlreadyExistException;
import com.example.testmanager.exceptions.NotFounElementException;
import com.example.testmanager.mappers.SpecimenMapper;
import com.example.testmanager.model.Program;
import com.example.testmanager.model.Specimen;
import com.example.testmanager.repository.ProgramRepository;
import com.example.testmanager.repository.SpecimenRepository;
import com.example.testmanager.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecimenService {
    private final Validator validator;
    private final SpecimenRepository specimenRepository;
    private final ProgramRepository programRepository;
    private final SpecimenMapper specimenMapper;

    public Object createSpecimen(Long programId, NewSpecimenDto newSpecimen) {
        validator.validateNewSpecimenDto(newSpecimen);
        newSpecimen.setProgram(programId);
        log.info("Получен запрос на сохранение данных по образцу {}", newSpecimen.getMarking());
        if (specimenRepository.findAll()
                .stream()
                .anyMatch(u -> u.getMarking().equals(newSpecimen.getMarking()) && u.getProgram().getId() == newSpecimen.getProgram())) {
            throw new DataAlreadyExistException("Данные по этому образцу уже внесены", "Необходимо редактировать данные",
                    LocalDateTime.now());
        }
        Specimen toSave = specimenMapper.INSTANCE.toSpecimen(newSpecimen);
        Program storedProgram = programRepository.findById(programId).orElseThrow(() -> new NotFounElementException("Программа с id" + programId +
                "не найдена", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        toSave.setProgram(storedProgram);
        return specimenMapper.INSTANCE.toSpecimenDto(specimenRepository.save(toSave));
    }

    public Object update(Long specimenId, SpecimenDtoUpd specimenDtoUpd) {
        Specimen stored = specimenRepository.findById(specimenId).orElseThrow(() -> new NotFounElementException("Образец с id" + specimenId +
                "не найден", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        log.debug("Получен запрос на обновление данных по образцу {}", stored.getMarking());
        Specimen updated = specimenMapper.INSTANCE.updateSpecimen(specimenDtoUpd, stored);
        return specimenMapper.INSTANCE.toSpecimenDto(specimenRepository.save(updated));
    }

    public void deleteSpecimen(Long specimenId) {
        Specimen stored = specimenRepository.findById(specimenId).orElseThrow(() -> new NotFounElementException("Образец с id" + specimenId +
                "не найден", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        log.debug("Получен запрос на удаление данных образца {}", stored.getMarking());
        specimenRepository.deleteById(specimenId);
        log.info("Данные по образцу {} удалены", stored.getMarking());
    }

    public Object getSpecimenById(Long specimenId) {
        log.debug("Получен запрос на получение данных образца {}", specimenId);
        Specimen stored = specimenRepository.findById(specimenId).orElseThrow(() -> new NotFounElementException("Образец с id" + specimenId +
                "не найден", "Запрашиваемый объект не найден или не доступен",
                LocalDateTime.now()));
        return SpecimenMapper.INSTANCE.toSpecimenDto(stored);
    }

    public Object getSpecimens(List<Long> programs, String standard, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "id"));
        if(programs != null || standard != null) {
            log.info("Получение информации об образцах с фильтром");
            Predicate predicate = createPredicate(programs, standard).getValue();
            List<SpecimenDto> allSpecimens = specimenRepository.findAll(Objects.requireNonNull(predicate), pageable).getContent()
                    .stream()
                    .map(SpecimenMapper.INSTANCE::toSpecimenDto)
                    .collect(Collectors.toList());
            return allSpecimens;
        } else {
            return specimenRepository.findAll()
                    .stream()
                    .map(SpecimenMapper.INSTANCE::toSpecimenDto)
                    .collect(Collectors.toList());
        }
    }

    private BooleanBuilder createPredicate(List<Long> programs, String standard) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QSpecimen qSpecimen = QSpecimen.specimen;
         if (programs != null){
             booleanBuilder.and(QSpecimen.specimen.program.id.in(programs));
         }
         if (standard != null){
             booleanBuilder.and(QSpecimen.specimen.standard.eq(standard));
         }
        return booleanBuilder;
    }
}
