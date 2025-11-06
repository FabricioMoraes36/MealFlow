package com.dev.MealFood.Services;


import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.StatusPedido;
import com.dev.MealFood.Models.Mesa;
import com.dev.MealFood.Models.Pedido;
import com.dev.MealFood.Repositories.MesaRepository;
import com.dev.MealFood.Repositories.PedidoRepository;
import com.dev.MealFood.Repositories.PratoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {


    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final PratoRepository pratoRepository;

    public PedidoService(PedidoRepository pedidoRepository, MesaRepository mesaRepository, PratoRepository pratoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.pratoRepository = pratoRepository;
    }

    public Pedido criarPedido(Long mesaId, Long garcomId){
        Mesa mesa = mesaRepository.findById(mesaId).orElseThrow(()->
                new RuntimeException("Mesa não encontrada"));


        boolean pedidoAberto = pedidoRepository.existsByMesaIdAndStatus(mesaId, StatusPedido.ABERTO);

        if(pedidoAberto){
            throw new RuntimeException("Já existe um pedido em aberto para essa mesa");
        }
        else {
            Pedido pedido = new Pedido();
            pedido.setGarcom(mesa.getGarcom());
            pedido.setDataHoraAbertura(LocalDateTime.now());
            pedido.setStatus(StatusPedido.ABERTO);

            // Use helper method to maintain bidirectional relationship
            mesa.addPedido(pedido);

            mesa.setStatus(MesaStatus.OCUPADA);
            mesaRepository.save(mesa);


            return pedidoRepository.save(pedido);
        }
    }
}
