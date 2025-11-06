package com.dev.MealFood.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Garcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Turno é obrigatório")
    private Turno turno;

    @OneToMany(mappedBy = "garcom")
    private List<Mesa> mesas;
}
