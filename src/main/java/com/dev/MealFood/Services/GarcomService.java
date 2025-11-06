package com.dev.MealFood.Services;


import com.dev.MealFood.Models.Garcom;
import com.dev.MealFood.Repositories.GarcomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    private final GarcomRepository repository;

    public GarcomService(GarcomRepository repository) {
        this.repository = repository;
    }

    public Garcom criar(Garcom garcom) {
        return repository.save(garcom);
    }

    public List<Garcom> listar() {
        return repository.findAll();
    }

    public Garcom buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garçom não encontrado!"));
    }

    @Transactional
    public Garcom atribuirMesa(Long garcomId, Long mesaId) {

        Garcom garcom = buscarPorId(garcomId);
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        // Regra - Garçom só pode ter até 5 mesas >:c
        if (garcom.getMesas().size() >= 5) {
            throw new RuntimeException("Esse garçom já atende o máximo de 5 mesas");
        }

        // Regra - Garçom deve ter o mesmo Turno da Mesa
        if (!mesa.getTurno().equals(garcom.getTurno())) {
            throw new RuntimeException("Garçom só pode atender mesas do mesmo Turno");
        }

        mesa.setGarcom(garcom);
        garcom.getMesas().add(mesa);

        mesaRepository.save(mesa);
        return repository.save(garcom);
    }
}
