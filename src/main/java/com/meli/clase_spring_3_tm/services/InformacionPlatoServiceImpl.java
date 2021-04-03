package com.meli.clase_spring_3_tm.services;

import com.meli.clase_spring_3_tm.dto.InformacionPlatoDTO;
import com.meli.clase_spring_3_tm.dto.IngredienteDTO;
import com.meli.clase_spring_3_tm.dto.PlatoDTO;
import com.meli.clase_spring_3_tm.repositories.IngredienteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformacionPlatoServiceImpl implements InformacionPlatoService {
    @Autowired
    IngredienteRepositoryImpl ingredienteRepository;

    @Override
    public InformacionPlatoDTO getInformation(PlatoDTO plato) {
        this.setCaloriasIngredientes(plato.getIngredients());
        System.out.println(plato.getIngredients());
        if(plato.getIngredients().size() > 0)
        return new InformacionPlatoDTO(
                getCaloriasTotales(plato),
                getCaloriasPorIngrediente(plato),
                getIngredienteMasCalorico(plato));
        else return null;
    }

    private double getCaloriasTotales(PlatoDTO plato) {
        return plato.getIngredients().stream().mapToDouble(i -> getCaloriasPorIngrediente(i)).sum();
    }

    private double getCaloriasPorIngrediente(IngredienteDTO ingrediente) {
        return ingrediente.getCalories() * ingrediente.getWeight();
    }

    private void setCaloriasIngredientes(List<IngredienteDTO> ingredientes) {
        List<IngredienteDTO> ingredientesNoEncontrados = new ArrayList<>();
        ingredientes.forEach(i -> {
            IngredienteDTO ingredientFromRepository = ingredienteRepository.findIngrediente(i);
            if(ingredientFromRepository != null) {
                i.setCalories(ingredientFromRepository.getCalories());
            } else {
                ingredientesNoEncontrados.add(i);
            }
        });
        ingredientesNoEncontrados.stream().forEach(i -> {
            ingredientes.remove(i);
            System.out.println("El ingrediente " + i + " no fue encontrado en la base de datos.");
        });
    }

    private Map<IngredienteDTO,Double> getCaloriasPorIngrediente(PlatoDTO plato) {
        Map<IngredienteDTO,Double> caloriasPorIngrediente = new HashMap<>();
        plato.getIngredients().forEach(i -> caloriasPorIngrediente.put(i,this.getCaloriasPorIngrediente(i)));
        return caloriasPorIngrediente;
    }

    private IngredienteDTO getIngredienteMasCalorico(PlatoDTO plato) {
        IngredienteDTO ingredienteMasCalorico = plato.getIngredients().get(0);
        for (IngredienteDTO i: plato.getIngredients()) {
            if(getCaloriasPorIngrediente(i) > getCaloriasPorIngrediente(ingredienteMasCalorico))
                ingredienteMasCalorico = i;
        }
        return ingredienteMasCalorico;
    }
}
