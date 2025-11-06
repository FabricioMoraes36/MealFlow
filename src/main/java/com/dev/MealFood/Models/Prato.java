package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.PratoCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pratos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Prato {

    private UUID id;
    private String nome;
    private String preco;
    private PratoCategoria categoria;
    private List<Ingredientes> ingredientes;
}
