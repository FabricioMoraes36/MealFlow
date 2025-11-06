package com.dev.MealFood.Services;

import com.dev.MealFood.DTO.MesaRequest;
import com.dev.MealFood.DTO.MesaResponse;
import com.dev.MealFood.Models.Mesa;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MesaService {


    public MesaResponse criarMesa(MesaRequest mesaRequest){
        if (Objects.isNull(mesaRequest)) {
            throw new IllegalArgumentException("A mesa contém algum campo nulo");
        }
        return null;
    }
}
