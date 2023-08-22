package com.example.testmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewProgramDto {
    @NotBlank
    @Size(max = 50, min = 1)
    private String number;
    @NotBlank
    @Size(max = 100)
    private String annotation;
}
