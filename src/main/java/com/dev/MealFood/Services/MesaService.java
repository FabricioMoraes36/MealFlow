package com.dev.MealFood.Services;

import com.dev.MealFood.DTO.MesaRequest;
import com.dev.MealFood.DTO.MesaResponse;
import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.Turno;
import com.dev.MealFood.Models.Mesa;
import com.dev.MealFood.Repositories.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaResponse criarMesa(MesaRequest mesaRequest){
        if (Objects.isNull(mesaRequest)) {
            throw new IllegalArgumentException("A mesa contém algum campo nulo");
        }

        Mesa mesa = Mesa.builder()
                .numero(mesaRequest.numeroMesa())
                .status(MesaStatus.valueOf(mesaRequest.statusMesa()))
                .turnoMesa(Turno.valueOf(mesaRequest.turnoMesa()))
                .build();

        mesaRepository.save(mesa);

        return new MesaResponse(mesa.getId(), mesa.getNumero(), mesa.getStatus().name(), mesa.getTurnoMesa());
    }

    public MesaResponse obterMesaPorId(Long id){
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada com o ID: " + id));

        return new MesaResponse(mesa.getId(), mesa.getNumero(), mesa.getStatus().name(), mesa.getTurnoMesa());
    }

    public List<MesaResponse> listarTodasMesas(){

        return mesaRepository.findAll().stream().map(mesa ->
                new MesaResponse(mesa.getId(), mesa.getNumero(), mesa.getStatus().name(), mesa.getTurnoMesa())
        ).toList();
    }

    public void deletarMesaPorId(Long id){
        mesaRepository.deleteById(id);
    }

    public MesaResponse atualizarMesa(Long id, MesaRequest mesaRequest){
        Mesa mesaExistente = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada com o ID: " + id));

        if (Objects.nonNull(mesaRequest.numeroMesa())) {
            mesaExistente.setNumero(mesaRequest.numeroMesa());
        }
        if (Objects.nonNull(mesaRequest.statusMesa())) {
            mesaExistente.setStatus(MesaStatus.valueOf(mesaRequest.statusMesa()));
        }
        if (Objects.nonNull(mesaRequest.turnoMesa())) {
            mesaExistente.setTurnoMesa(Turno.valueOf(mesaRequest.turnoMesa()));
        }

        mesaRepository.save(mesaExistente);

        return new MesaResponse(mesaExistente.getId(), mesaExistente.getNumero(), mesaExistente.getStatus().name(), mesaExistente.getTurnoMesa());
    }

}
