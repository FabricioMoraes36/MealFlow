package com.dev.MealFood.Repositories;

import com.dev.MealFood.Models.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PratoRepository extends JpaRepository<Prato,Long> {

    Prato findPratoByNome(String nome);

    // Busca pratos que têm um ingrediente com o nome informado
    @Query("select distinct p from Prato p join p.pratoIngredientes pi join pi.ingrediente i where lower(i.nome) = lower(:nome)")
    List<Prato> findPratosWithIngredientName(@Param("nome") String nome);

    // Busca pratos que NÃO têm o ingrediente com o nome informado
    @Query("select p from Prato p where not exists (select pi from p.pratoIngredientes pi join pi.ingrediente i where lower(i.nome) = lower(:nome))")
    List<Prato> findPratosWithoutIngredientName(@Param("nome") String nome);
}
