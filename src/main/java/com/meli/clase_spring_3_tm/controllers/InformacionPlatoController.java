package com.meli.clase_spring_3_tm.controllers;

import com.meli.clase_spring_3_tm.dto.InformacionPlatoDTO;
import com.meli.clase_spring_3_tm.dto.PlatoDTO;
import com.meli.clase_spring_3_tm.services.InformacionPlatoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformacionPlatoController {
    @Autowired
    InformacionPlatoServiceImpl service;

    @PostMapping("calorias")
    public ResponseEntity<InformacionPlatoDTO> getInformacionPlato(@RequestBody PlatoDTO plato) {
        return new ResponseEntity<InformacionPlatoDTO>(this.service.getInformation(plato), HttpStatus.OK);
    }

}
