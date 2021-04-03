package com.meli.clase_spring_3_tm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteDTO {
    private String name;
    private double weight;
    private double calories;

    @Override
    public String toString() {
        return this.name;
    }
}
