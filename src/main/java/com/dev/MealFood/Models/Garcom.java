package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.Turno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Garcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Turno é obrigatório")
    private Turno turno;

    @JsonIgnore
    @OneToMany(mappedBy = "garcom")
    private List<Mesa> mesas = new ArrayList<>();

    // Helper methods for bidirectional relationship management
    public void addMesa(Mesa mesa) {
        if (!mesas.contains(mesa)) {
            mesas.add(mesa);
        }
        // Ensure the other side is also set correctly
        if (mesa.getGarcom() != this) {
            mesa.setGarcom(this);
        }
    }

    public void removeMesa(Mesa mesa) {
        if (mesas.contains(mesa)) {
            mesas.remove(mesa);
        }
        // Ensure the other side is also set correctly
        if (mesa.getGarcom() == this) {
            mesa.setGarcom(null);
        }
    }
}
