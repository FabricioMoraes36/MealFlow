package com.dev.MealFood.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatorioGarcomDTO {
    private Long garcomId;
    private String nome;
    private Double totalVendidoHoje;
}
