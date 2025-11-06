package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.PratoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pratos")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prato_nome", nullable = false)
    private String nome;

    @Column(name = "prato_preco", nullable = false)
    private BigDecimal preco = BigDecimal.ONE;

    @Enumerated(EnumType.STRING)
    @Column(name = "prato_categoria", nullable = false)
    private PratoCategoria categoria;

    // Substitui ManyToMany por uma entidade de junção explícita para garantir que a tabela
    // de junção tenha uma primary key (sql_require_primary_key do MySQL exige PK).
    @OneToMany(mappedBy = "prato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PratoIngrediente> pratoIngredientes = new ArrayList<>();

    @OneToMany(mappedBy = "prato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoPrato> pedidoPratos;
}
