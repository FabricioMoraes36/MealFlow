package com.dev.MealFood.Controllers;

import com.dev.MealFood.Models.Ingredientes;
import com.dev.MealFood.Services.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {


    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredientes>> findAll() {
        List<Ingredientes> lista = ingredienteService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredientes> findById(@PathVariable Long id) {
        Ingredientes ingrediente = ingredienteService.findById(id);
        return ResponseEntity.ok(ingrediente);
    }

    @PostMapping
    public ResponseEntity<Ingredientes> create(@RequestBody Ingredientes ingrediente) {
        Ingredientes criado = ingredienteService.create(ingrediente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.getId())
                .toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredientes> update(@PathVariable Long id, @RequestBody Ingredientes ingrediente) {
        Ingredientes atualizado = ingredienteService.update(id, ingrediente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
