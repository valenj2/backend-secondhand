package com.secondhand.backend.dtos;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto {
    private Long id;
    private LocalDate fechaCompra;
    private Long idUsuario;  // ID del usuario
    private Long idPrenda;   // ID de la prenda
}