package com.dev.MealFood.Repositories;

import com.dev.MealFood.Enums.StatusPedido;
import com.dev.MealFood.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.total), 0)
        FROM Pedido p
        WHERE p.garcom.id = :garcomId
        AND p.status = :status
        AND p.dataHoraFechamento BETWEEN :inicio AND :fim
    """)
    Double findByGarcomAndStatusAndData(
            @Param("garcomId") Long garcomId,
            @Param("status") StatusPedido status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}
