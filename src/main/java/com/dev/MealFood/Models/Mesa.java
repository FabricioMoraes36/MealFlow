package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.Turno;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero", nullable = false, unique = true)
    private Integer numero;

    @Column(name="status", nullable = false)
    private MesaStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Turno turno;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;

}
