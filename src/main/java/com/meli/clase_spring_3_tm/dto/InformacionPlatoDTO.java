package com.meli.clase_spring_3_tm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class InformacionPlatoDTO {
    private double calories;
    private Map<IngredienteDTO,Double> caloriasPorIngrediente;
    private IngredienteDTO ingredienteMasCalorico;
}
