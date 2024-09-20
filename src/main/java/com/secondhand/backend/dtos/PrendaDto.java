package com.secondhand.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrendaDto {
    private Long id;
    private Integer anno;
    private String descripcion;
    private String tipoTela;
    private Double precio;
    private String estado;
    private String categoriaPrenda;
    private String url;
}
