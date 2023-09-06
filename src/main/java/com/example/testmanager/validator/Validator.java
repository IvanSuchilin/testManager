package com.example.testmanager.validator;

import com.example.testmanager.dto.NewProgramDto;
import com.example.testmanager.dto.NewSpecimenDto;
import com.example.testmanager.exceptions.RequestValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class Validator {
    LocalDateTime time = LocalDateTime.now();

    public void validateNewProgramDto(NewProgramDto newProgramDto) {
        if (newProgramDto.getNumber().isBlank()) {
            throw new RequestValidationException("Не указан номер программы", "Необходимо указать номер программы", time);
        }
        if (newProgramDto.getAnnotation().isBlank()) {
            throw new RequestValidationException("Не указано описание программы", "Необходимо указать описание" +
                    " программы", time);
        }
    }

    public void validateNewSpecimenDto(NewSpecimenDto newSpecimenDto) {
        if (newSpecimenDto.getMarking().isBlank()) {
            throw new RequestValidationException("Не указан номер образца", "Необходимо указать номер образца", time);
        }
        if (newSpecimenDto.getProtocol().isBlank()) {
            throw new RequestValidationException("Не указан номер протокола испытаний", "Необходимо указать номер протокола испытаний", time);
        }

        if (newSpecimenDto.getStandard().isBlank()) {
            throw new RequestValidationException("Не указан стандарт испытания", "Номер стандарта пуст", time);
        }
    }
}
