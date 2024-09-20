package com.secondhand.backend.controllers;

import com.secondhand.backend.dtos.PrendaDto;
import com.secondhand.backend.entities.Prenda;
import com.secondhand.backend.services.PrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prendas")
public class PrendaController {

    @Autowired
    private PrendaService prendaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPrendas() {
        List<Prenda> prendas = prendaService.getAllPrendas();

        Map<String, Object> response = new HashMap<>();
        response.put("products", prendas.stream().map(this::convertToDto).collect(Collectors.toList()));
        response.put("totalCount", prendas.size());
        response.put("resultado", "true");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public PrendaDto getPrendaById(@PathVariable Long id) {
        Prenda prenda = prendaService.getPrendaById(id);
        return convertToDto(prenda);
    }

    @PostMapping
    public PrendaDto savePrenda(@RequestBody PrendaDto prendaDto) {
        Prenda prenda = convertToEntity(prendaDto);
        Prenda newPrenda = prendaService.savePrenda(prenda);
        return convertToDto(newPrenda);
    }

    @DeleteMapping("/{id}")
    public void deletePrenda(@PathVariable Long id) {
        prendaService.deletePrenda(id);
    }

    private PrendaDto convertToDto(Prenda prenda) {
        PrendaDto prendaDto = new PrendaDto();
        prendaDto.setId(prenda.getId());
        prendaDto.setAnno(prenda.getAnno());
        prendaDto.setTipoTela(prenda.getTipotela());
        prendaDto.setDescripcion(prenda.getDescripcion());
        prendaDto.setPrecio(prenda.getPrecio());
        prendaDto.setEstado(prenda.getEstado());
        prendaDto.setCategoriaPrenda(prenda.getCategoria().getCategoria_prenda());
        prendaDto.setUrl(prenda.getUrl());
        return prendaDto;
    }

    private Prenda convertToEntity(PrendaDto prendaDto) {
        Prenda prenda = new Prenda();
        prenda.setId(prendaDto.getId());
        prenda.setAnno(prendaDto.getAnno());
        prenda.setTipotela(prendaDto.getTipoTela());
        prenda.setDescripcion(prendaDto.getDescripcion());
        prenda.setPrecio(prendaDto.getPrecio());
        prenda.setEstado(prendaDto.getEstado());
        prenda.setUrl(prendaDto.getUrl());
        return prenda;
    }
}
