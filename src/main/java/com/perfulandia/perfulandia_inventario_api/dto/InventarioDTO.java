package com.perfulandia.perfulandia_inventario_api.dto;

import lombok.Data;

@Data
public class InventarioDTO {
    private Integer id_inventario;
    private Integer id_producto;
    private int stock_disponible;
    private String ubicacion_bodega;
}
