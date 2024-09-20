package com.secondhand.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prendas")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer anno;

    @Column(name = "tipo_tela")
    private String tipotela;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "categoria_prenda")
    private CategoriaPrenda categoria;

    @Column
    @OneToMany(mappedBy="prenda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Compra> compras;

    @Column(name = "url")  
    private String url;
}
