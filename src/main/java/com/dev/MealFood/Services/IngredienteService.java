package com.dev.MealFood.Services;

import com.dev.MealFood.Models.Ingredientes;
import com.dev.MealFood.Repositories.IngredienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingredientes> findAll() {
        return ingredienteRepository.findAll();
    }

    public Ingredientes findById(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    @Transactional
    public Ingredientes create(Ingredientes ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @Transactional
    public Ingredientes update(Long id, Ingredientes ingrediente) {
        return ingredienteRepository.findById(id)
                .map(existing -> {
                    BeanUtils.copyProperties(ingrediente, existing, "id");
                    return ingredienteRepository.save(existing);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    @Transactional
    public void delete(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado");
        }
        ingredienteRepository.deleteById(id);
    }
}
