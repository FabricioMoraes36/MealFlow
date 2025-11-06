package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.UnidadeMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_ingrediente")
    private String nome;

    @Column(name = "quantidade_em_estoque_ingrediente")
    private double quantidadeEmEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida_ingrediente")
    private UnidadeMedida unidadeMedida;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Prato> pratos = new ArrayList<>();
}
