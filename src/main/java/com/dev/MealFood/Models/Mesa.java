package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.MesaStatus;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="status", nullable = false)
    private MesaStatus status;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    @Column(name="pedidos")
    private List<Pedido> pedidos;
}
