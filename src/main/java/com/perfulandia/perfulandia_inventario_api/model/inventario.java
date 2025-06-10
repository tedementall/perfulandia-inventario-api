package com.perfulandia.perfulandia_inventario_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventario")
@Data

public class inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_inventario;

    @Column(name = "id_producto", nullable = false)
    private Integer Id_producto;

    @Column(name = "stock_disponible",nullable = false)
    private int stock_disponible;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;


}
