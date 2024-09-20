package com.secondhand.backend.services;

import com.secondhand.backend.entities.Compra;
import com.secondhand.backend.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    public Compra getCompraById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    public Compra saveCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
