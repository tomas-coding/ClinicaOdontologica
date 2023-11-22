package com.clinicaodontologica.Clinica.Odontologica.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Data
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

}
