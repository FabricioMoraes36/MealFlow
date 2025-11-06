package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.UnidadeMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

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
    private UnidadeMedida unidadeMedida;

    // Substitui ManyToMany por uma entidade de junção explícita para garantir PK na tabela de junção
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PratoIngrediente> pratoIngredientes = new ArrayList<>();
}
