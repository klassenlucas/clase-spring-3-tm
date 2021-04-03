package com.meli.clase_spring_3_tm.repositories;

import com.meli.clase_spring_3_tm.dto.IngredienteDTO;

public interface IngredienteRepository {
    public IngredienteDTO findIngrediente(IngredienteDTO ingrediente);
}
