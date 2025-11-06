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
        if (pedido == null) {
            return;
        }
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        if (pedidos.contains(pedido)) {
            return;
        }
        pedidos.add(pedido);
        // Synchronize the other side only if needed (prevents recursion)
        if (pedido.getMesa() != this) {
            pedido.setMesa(this);
        }
    }

    public void removePedido(Pedido pedido) {
        if (pedido == null || pedidos == null) {
            return;
        }
        if (!pedidos.contains(pedido)) {
            return;
        }
        pedidos.remove(pedido);
        // Synchronize the other side only if needed (prevents recursion)
        if (pedido.getMesa() == this) {
            pedido.setMesa(null);
        }
    }

    public void setGarcom(Garcom garcom) {
        // Handle removal from old garcom
        if (this.garcom != null && this.garcom != garcom) {
            List<Mesa> oldGarcomMesas = this.garcom.getMesas();
            if (oldGarcomMesas != null && oldGarcomMesas.contains(this)) {
                oldGarcomMesas.remove(this);
            }
        }
        // Update the field
        this.garcom = garcom;
        // Add to new garcom collection if needed
        if (garcom != null) {
            List<Mesa> newGarcomMesas = garcom.getMesas();
            if (newGarcomMesas != null && !newGarcomMesas.contains(this)) {
                newGarcomMesas.add(this);
            }
        }
    }

}
