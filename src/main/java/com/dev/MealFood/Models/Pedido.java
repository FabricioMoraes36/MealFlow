package com.dev.MealFood.Models;


import com.dev.MealFood.Enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "pedidos")
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pedido")
    private String nome;

    @Column (name = "preco_pedido")
    private double preco;

    @Column (name = "total_pedido")
    private double total;

    private LocalDateTime dataHoraAbertura;

    private LocalDateTime dataHoraFechamento;


    @Column(name = "status_pedido")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;

    @ManyToMany
    @JoinTable(
            name = "pedido_prato",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id")
    )
    @Builder.Default
    private List<Prato> pratos = new ArrayList<>();

    // Métodos auxiliares para manter consistência
    public void addPrato(Prato prato) {
        pratos.add(prato);
    }

    public void removePrato(Prato prato) {
        pratos.remove(prato);
    }
}
