package com.perfulandia.perfulandia_inventario_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio_unitario", nullable = false)
    private double precio_unitario;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "activo", nullable = false)
    private boolean activo;
}
