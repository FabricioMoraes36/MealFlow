package com.dev.MealFood.Controllers;

import com.dev.MealFood.DTO.MesaRequest;
import com.dev.MealFood.DTO.MesaResponse;
import com.dev.MealFood.Models.Mesa;
import com.dev.MealFood.Repositories.MesaRepository;
import com.dev.MealFood.Services.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @PostMapping("/criar")
    public ResponseEntity<MesaResponse> criarMesa(MesaRequest mesaRequest){

        MesaResponse newMesa = mesaService.criarMesa(mesaRequest);

        return ResponseEntity.ok(newMesa);
    }

    @GetMapping("/obterPorId/{id}")
    public ResponseEntity<MesaResponse> obterMesaPorId(@PathVariable Long id){
        MesaResponse mesa = mesaService.obterMesaPorId(id);

        return ResponseEntity.ok(mesa);
    }

    @GetMapping("/listarTodas")
    public ResponseEntity<List<MesaResponse>> listarTodasMesas(){
        List<MesaResponse> mesas = mesaService.listarTodasMesas();

        return ResponseEntity.ok(mesas);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<MesaResponse> atualizarMesa(@PathVariable Long id, MesaRequest mesaRequest){
        MesaResponse updatedMesa = mesaService.atualizarMesa(id, mesaRequest);

        return ResponseEntity.ok(updatedMesa);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMesaPorId(@PathVariable Long id){
        mesaService.deletarMesaPorId(id);

        return ResponseEntity.noContent().build();
    }
}
