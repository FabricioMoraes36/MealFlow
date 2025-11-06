package com.dev.MealFood.Controllers;

import com.dev.MealFood.Models.Garcom;
import com.dev.MealFood.Services.GarcomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garcons")
public class GarcomController {

    private final GarcomService service;

    public GarcomController(GarcomService service) {
        this.service = service;
    }

    @PostMapping
    public Garcom criar(@RequestBody Garcom garcom) {
        return service.criar(garcom);
    }

    @GetMapping
    public List<Garcom> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Garcom buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PatchMapping("/{garcomId}/mesas/{mesaId}")
    public Garcom atribuirMesa(
            @PathVariable Long garcomId,
            @PathVariable Long mesaId
    ) {
        return service.atribuirMesa(garcomId, mesaId);
    }
}
