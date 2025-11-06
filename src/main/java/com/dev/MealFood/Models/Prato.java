package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.PratoCategoria;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "prato_nome", nullable = false)
    private String nome;

    @Column(name = "prato_preco", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "prato_categoria", nullable = false)
    private PratoCategoria categoria;

    @ManyToMany
    @JoinTable(
            name = "prato_ingrediente",
            joinColumns = @JoinColumn(name = "prato_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingredientes> ingredientes;
}
