package com.secondhand.backend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "compras")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate fechacompra;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //   @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;


}