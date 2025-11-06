package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.PratoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pratos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "prato_nome")
    private String nome;

    @Column(name = "prato_preco")
    private BigDecimal preco;

    @Column(name = "prato_categoria")
    @Enumerated(EnumType.STRING)
    private PratoCategoria categoria;


    @ManyToOne
    @JoinTable(name = "prato_ingrediente",
    joinColumns = @JoinColumn(name = "prato_id"),
    inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingredientes> ingredientes;

}
