package com.dev.MealFood.Models;


import com.dev.MealFood.Enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoPrato> pedidoPratos;




}
