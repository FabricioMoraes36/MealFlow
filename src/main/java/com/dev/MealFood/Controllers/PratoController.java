package com.dev.MealFood.Controllers;

import com.dev.MealFood.Models.Prato;
import com.dev.MealFood.Services.PratoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {
    private PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping("/sem-ingrediente")
    public ResponseEntity<List<Prato>> findPratosWithoutIngredientName(@RequestParam("nome") String nome) {
        List<Prato> pratos = pratoService.findWithoutIngredientName(nome);
        return ResponseEntity.ok(pratos);
    }
    @GetMapping("/com-ingrediente")
    public ResponseEntity<List<Prato>> findPratosWithIngredientName(@RequestParam("nome") String nome) {
        List<Prato> pratos = pratoService.findByIngredientName(nome);
        return ResponseEntity.ok(pratos);
    }
    @GetMapping("/todos-pratos")
    public ResponseEntity<List<Prato>> findAll(){
        return ResponseEntity.ok(pratoService.findAll());
    }
    @PostMapping("/criar-prato")
    public ResponseEntity<Prato> createPrato(@RequestBody Prato prato){
        return ResponseEntity.ok(pratoService.createPrato(prato));
    }
    @PutMapping("/atualizar-prato/{nome}")
    public ResponseEntity<Prato> updatePratoByName(@PathVariable String nome, @RequestBody Prato pratoDetails){
        Prato prato = pratoService.updatePratoByName(nome, pratoDetails);
        return ResponseEntity.ok(prato);
    }

}
