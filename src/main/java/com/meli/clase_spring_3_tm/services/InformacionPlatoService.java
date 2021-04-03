package com.meli.clase_spring_3_tm.services;

import com.meli.clase_spring_3_tm.dto.InformacionPlatoDTO;
import com.meli.clase_spring_3_tm.dto.PlatoDTO;

public interface InformacionPlatoService {
    public InformacionPlatoDTO getInformation(PlatoDTO plato);
}
