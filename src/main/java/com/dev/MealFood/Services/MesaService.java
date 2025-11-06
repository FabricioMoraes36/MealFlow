package com.dev.MealFood.Services;

import com.dev.MealFood.DTO.MesaAttRequest;
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

        Mesa mesa = new Mesa();

        mesa.setNumero(mesaRequest.numeroMesa());
        mesa.setStatus(MesaStatus.valueOf("LIVRE"));
        mesa.setTurnoMesa(Turno.valueOf("MANHA"));

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

    public MesaResponse atualizarMesa(Long id, MesaAttRequest mesaAttRequest){
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada com o ID: " + id));

        if (Objects.nonNull(mesaAttRequest.numeroMesa())) {
            mesa.setNumero(mesaAttRequest.numeroMesa());
        }
        if (Objects.nonNull(mesa.getStatus())) {
            mesa.setStatus(MesaStatus.valueOf(mesaAttRequest.statusMesa()));
        }
        if (Objects.nonNull(mesaAttRequest.turnoMesa())) {
            mesa.setTurnoMesa(Turno.valueOf(mesaAttRequest.turnoMesa()));
        }

        mesaRepository.save(mesa);

        return new MesaResponse(mesa.getId(), mesa.getNumero(), mesa.getStatus().name(), mesa.getTurnoMesa());
    }

}
