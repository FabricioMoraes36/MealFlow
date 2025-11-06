package com.dev.MealFood.Models;


import com.dev.MealFood.Enums.StatusPedido;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {


    @Id
    private Long id;

    @Column(name = "nome_pedido")
    private String nome;

    @Column (name = "preco_pedido")
    private double preco;

    @Column (name = "total_pedido")
    private double total;

    @Column(name = "status_pedido")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany
    private Mesa mesa;


    @OneToMany
    private Garcom garcom;


    @ManyToMany
    @JoinTable(
            name = "pedido_prato",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id")
    )
    private List<Prato> pratos;




}
