package com.dev.MealFood.DTO;

import com.dev.MealFood.Enums.Turno;

public record MesaResponse (Long id, Long numeroMesa, String statusMesa, Turno turnoMesa){
}
