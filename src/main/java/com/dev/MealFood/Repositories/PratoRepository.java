package com.dev.MealFood.Repositories;

import com.dev.MealFood.Models.Ingredientes;
import com.dev.MealFood.Models.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PratoRepository extends JpaRepository<Prato,Long> {

    Prato findPratoByName(String nome);

    @Query("select p from Prato p join p.ingredientes i where lower(i.nome) = lower(:nome)")
    List<Prato> findPratosWithIngredientName(@Param("nome") String nome);

    @Query("select p from Prato p where not exists (select i from p.ingredientes i where lower(i.nome) = lower(:nome))")
    List<Prato> findPratosWithoutIngredientName(@Param("nome") String nome);
}
