package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.Turno;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesas")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero", nullable = false, unique = true)
    private Long numero;

    @Column(name="status", nullable = false)
    private MesaStatus status;

    private Turno turnoMesa;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;

    // Métodos auxiliares para manter consistência bidirecional
    public void addPedido(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
            pedido.setMesa(this);
        }
    }

    public void removePedido(Pedido pedido) {
        if (pedidos.contains(pedido)) {
            pedidos.remove(pedido);
            pedido.setMesa(null);
        }
    }
}
