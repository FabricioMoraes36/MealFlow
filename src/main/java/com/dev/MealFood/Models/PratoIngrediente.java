package com.dev.MealFood.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prato_ingrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PratoIngrediente {

    @EmbeddedId
    private PratoIngredienteId id;

    @ManyToOne
    @MapsId("pratoId")
    @JoinColumn(name = "prato_id")
    private Prato prato;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingredientes ingrediente;

    @Column(nullable = false)
    private double quantidade;
}

