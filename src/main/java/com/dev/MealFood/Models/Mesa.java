package com.dev.MealFood.Models;

import com.dev.MealFood.Enums.MesaStatus;
import com.dev.MealFood.Enums.Turno;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesas")
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero", nullable = false, unique = true)
    private Long numero;

    @Column(name="status", nullable = false)
    private MesaStatus status;

    private Turno turnoMesa;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;

    // Custom constructor for Builder compatibility with collection initialization
    public Mesa(Long id, Long numero, MesaStatus status, Turno turnoMesa, List<Pedido> pedidos, Garcom garcom) {
        this.id = id;
        this.numero = numero;
        this.status = status;
        this.turnoMesa = turnoMesa;
        this.pedidos = pedidos != null ? pedidos : new ArrayList<>();
        this.garcom = garcom;
    }

    // Helper methods for bidirectional relationship management
    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setMesa(this);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setMesa(null);
    }

    public void setGarcom(Garcom garcom) {
        // Remove from old garcom if exists
        if (this.garcom != null && this.garcom.getMesas().contains(this)) {
            this.garcom.getMesas().remove(this);
        }
        this.garcom = garcom;
        // Add to new garcom if not null
        if (garcom != null && !garcom.getMesas().contains(this)) {
            garcom.getMesas().add(this);
        }
    }

}
