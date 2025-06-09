package com.perfulandia.perfulandia_inventario_api.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private double precio_unitario;
    private String categoria;
    private boolean activo;
}
