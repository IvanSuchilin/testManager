package com.example.testmanager.dto;

import com.example.testmanager.model.Specimen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewSpecimenDto {
    @NotNull
    @Positive
    private Long program;
    @NotBlank
    @Size(max = 20, min = 1)
    private String marking;
    private Specimen.Standard standard;
    @NotBlank
    @Size(max = 50, min = 1)
    private String protocol;
}
