package com.secondhand.backend.controllers;

import com.secondhand.backend.dtos.CompraDto;
import com.secondhand.backend.entities.Compra;
import com.secondhand.backend.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<CompraDto> getAllCompras() {
        List<Compra> compras = compraService.getAllCompras();
        return compras.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CompraDto getCompraById(@PathVariable Long id) {
        Compra compra = compraService.getCompraById(id);
        return convertToDto(compra);
    }

    @PostMapping
    public CompraDto saveCompra(@RequestBody CompraDto compraDto) {
        Compra compra = convertToEntity(compraDto);
        Compra newCompra = compraService.saveCompra(compra);
        return convertToDto(newCompra);
    }

    @DeleteMapping("/{id}")
    public void deleteCompra(@PathVariable Long id) {
        compraService.deleteCompra(id);
    }

    private CompraDto convertToDto(Compra compra) {
        CompraDto compraDto = new CompraDto();
        compraDto.setId(compra.getId());
        compraDto.setFechaCompra(compra.getFechacompra());
        compraDto.setIdUsuario(compra.getUsuario().getId());
        compraDto.setIdPrenda(compra.getPrenda().getId());
        return compraDto;
    }

    private Compra convertToEntity(CompraDto compraDto) {
        Compra compra = new Compra();
        compra.setId(compraDto.getId());
        compra.setFechacompra(compraDto.getFechaCompra());
        // Configurar el usuario y la prenda (necesitarás agregar lógica para obtener ambos)
        return compra;
    }
}
