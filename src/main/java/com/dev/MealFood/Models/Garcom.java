package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.Turno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "garcons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Garcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @JsonIgnore
    @OneToMany(mappedBy = "garcom")
    private List<Mesa> mesas = new ArrayList<>();
}
