package com.dev.MealFood.Models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pedido_prato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoPrato {

    @EmbeddedId
    private PedidoPratoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("pratoId")
    @JoinColumn(name = "prato_id")
    private Prato prato;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double precoUnitario;
}