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
        if (mesa == null) {
            return;
        }
        // Check if already added to prevent duplicates
        if (mesas.contains(mesa)) {
            return;
        }
        mesas.add(mesa);
        // Synchronize the other side only if needed (prevents recursion)
        if (mesa.getGarcom() != this) {
            mesa.setGarcom(this);
        }
    }

    public void removeMesa(Mesa mesa) {
        if (mesa == null) {
            return;
        }
        if (!mesas.contains(mesa)) {
            return;
        }
        mesas.remove(mesa);
        // Synchronize the other side only if needed (prevents recursion)
        if (mesa.getGarcom() == this) {
            mesa.setGarcom(null);
        }
    }
}
