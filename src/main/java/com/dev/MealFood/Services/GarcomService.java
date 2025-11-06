package com.dev.MealFood.Services;


import com.dev.MealFood.DTO.RelatorioGarcomDTO;
import com.dev.MealFood.Enums.StatusPedido;
import com.dev.MealFood.Models.Garcom;
import com.dev.MealFood.Models.Mesa;
import com.dev.MealFood.Repositories.GarcomRepository;
import com.dev.MealFood.Repositories.MesaRepository;
import com.dev.MealFood.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GarcomService {

    private final GarcomRepository repository;
    private final MesaRepository mesaRepository;
    private final PedidoRepository pedidoRepository;

    public GarcomService(GarcomRepository repository,
                         MesaRepository mesaRepository,
                         PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
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
        if (!mesa.getTurnoMesa().equals(garcom.getTurno())) {
            throw new RuntimeException("Garçom só pode atender mesas do mesmo Turno");
        }

        // Use helper method to maintain bidirectional relationship
        garcom.addMesa(mesa);

        mesaRepository.save(mesa);
        return repository.save(garcom);
    }

    public List<RelatorioGarcomDTO> relatorioGarcons() {

        LocalDate hoje = LocalDate.now();

        List<Garcom> garcons = repository.findAll();

        return garcons.stream().map(garcom -> {

            double total = pedidoRepository.findByGarcomAndStatusAndData(
                    garcom.getId(),
                    StatusPedido.ENTREGUE,
                    hoje.atStartOfDay(),
                    hoje.plusDays(1).atStartOfDay()
            );

            return new RelatorioGarcomDTO(
                    garcom.getId(),
                    garcom.getNome(),
                    total
            );
        }).toList();
    }
}
