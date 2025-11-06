package com.dev.MealFood.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PratoIngredienteId implements Serializable {

    @Column(name = "prato_id")
    private Long pratoId;

    @Column(name = "ingrediente_id")
    private Long ingredienteId;
}

