package com.medilabo.riskservice.constants;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class RiskTriggers {

    private List<String> triggers = Arrays.asList(
            "hémoglobine a1c",
            "hémoglobine",
            "a1c",
            "microalbumine",
            "poids",
            "fumeur",
            "fumeuse",
            "fume",
            "anormal",
            "anormale",
            "cholestérol",
            "vertiges",
            "vertige",
            "rechute",
            "réaction",
            "anticorps");

}
