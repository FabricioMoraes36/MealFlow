package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.PratoCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pratos")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prato_nome", nullable = false)
    private String nome;

    @Column(name = "prato_preco", nullable = false)
    @Builder.Default
    private BigDecimal preco = BigDecimal.ONE;

    @Enumerated(EnumType.STRING)
    @Column(name = "prato_categoria", nullable = false)
    private PratoCategoria categoria;

    @ManyToMany
    @JoinTable(
            name = "prato_ingrediente",
            joinColumns = @JoinColumn(name = "prato_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    @Builder.Default
    private List<Ingredientes> ingredientes = new ArrayList<>();

    // Métodos auxiliares para manter consistência
    public void addIngrediente(Ingredientes ingrediente) {
        ingredientes.add(ingrediente);
        ingrediente.getPratos().add(this);
    }

    public void removeIngrediente(Ingredientes ingrediente) {
        ingredientes.remove(ingrediente);
        ingrediente.getPratos().remove(this);
    }
}
