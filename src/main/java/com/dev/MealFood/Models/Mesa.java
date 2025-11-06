package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.Turno;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mesas")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero", nullable = false, unique = true)
    private Long numero;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private MesaStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="turnoMesa", nullable = false)
    private Turno turnoMesa;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ManyToOne
    private Garcom garcom;

}
