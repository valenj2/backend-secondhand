package com.secondhand.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
// @Builder
@Table(name = "categoria_prendas")
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
@Getter
@Setter
public class CategoriaPrenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoria_prenda;

    @Column
    @JsonManagedReference
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenda> prendas;
}
