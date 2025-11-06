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
@AllArgsConstructor
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

    // Helper methods for bidirectional relationship management
    public void addPedido(Pedido pedido) {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        pedidos.add(pedido);
        pedido.setMesa(this);
    }

    public void removePedido(Pedido pedido) {
        if (pedidos != null) {
            pedidos.remove(pedido);
            pedido.setMesa(null);
        }
    }

    public void setGarcom(Garcom garcom) {
        // Remove from old garcom if exists
        if (this.garcom != null && this.garcom != garcom) {
            List<Mesa> oldGarcomMesas = this.garcom.getMesas();
            if (oldGarcomMesas.contains(this)) {
                oldGarcomMesas.remove(this);
            }
        }
        this.garcom = garcom;
        // Add to new garcom if not null
        if (garcom != null && !garcom.getMesas().contains(this)) {
            garcom.getMesas().add(this);
        }
    }

}
