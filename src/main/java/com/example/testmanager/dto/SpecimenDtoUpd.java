package com.example.testmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecimenDtoUpd {
    private String marking;
    private String standard;
    private String protocol;
    private Double strength;
    private Double module;
}
