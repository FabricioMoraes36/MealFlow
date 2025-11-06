package com.dev.MealFood.Repositories;

import com.dev.MealFood.Models.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingredientes,Long> {
}
