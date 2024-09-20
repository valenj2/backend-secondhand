package com.secondhand.backend.services;

import com.secondhand.backend.entities.Prenda;
import com.secondhand.backend.repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService {
    @Autowired
    private PrendaRepository prendaRepository;

    public List<Prenda> getAllPrendas() {
        return prendaRepository.findAll();
    }

    public Prenda getPrendaById(Long id) {
        return prendaRepository.findById(id).orElse(null);
    }

    public Prenda savePrenda(Prenda prenda) {
        return prendaRepository.save(prenda);
    }

    public void deletePrenda(Long id) {
        prendaRepository.deleteById(id);
    }
}
