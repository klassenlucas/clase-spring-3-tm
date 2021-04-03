package com.meli.clase_spring_3_tm.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.clase_spring_3_tm.dto.IngredienteDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredienteRepositoryImpl implements IngredienteRepository {
    @Override
    public IngredienteDTO findIngrediente(IngredienteDTO ingrediente) {
        List<IngredienteDTO> ingredientes = null;
        ingredientes = loadDataBase();
        IngredienteDTO result = null;
        if(ingredientes!=null) {
            Optional<IngredienteDTO> ingredienteDTO = ingredientes.stream().filter(i -> i.getName().equals(ingrediente.getName())).findFirst();
        if(ingredienteDTO.isPresent()) result = ingredienteDTO.get();
        }
        return result;
    }

    private List<IngredienteDTO> loadDataBase() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:food.json");

        } catch(Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredienteDTO>> typeReference = new TypeReference<>() {};
        List<IngredienteDTO> ingredientes = null;
        try {
            ingredientes = objectMapper.readValue(file,typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientes;
    }
}
