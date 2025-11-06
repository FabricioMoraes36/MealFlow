package com.dev.MealFood.Controllers;

import com.dev.MealFood.Models.Pedido;
import com.dev.MealFood.Services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar-pedido")
    public ResponseEntity<Pedido> criarPedido(
            @RequestParam Long mesaId,
            @RequestParam Long garcomId) {

        Pedido novoPedido = pedidoService.criarPedido(mesaId, garcomId);
        return ResponseEntity.ok(novoPedido);
    }
}
