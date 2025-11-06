package com.dev.MealFood.Models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoPratoId implements Serializable {

    private Long pedidoId;
    private Long pratoId;
}