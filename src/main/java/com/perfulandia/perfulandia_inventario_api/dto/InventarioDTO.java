package com.perfulandia.perfulandia_inventario_api.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO extends RepresentationModel <InventarioDTO> {
    private Integer id_inventario;
    private Integer id_producto;
    private int stock_disponible;
    private String ubicacion_bodega;

    public Integer getId() {
        return id_inventario;
    }

    public void setId(Integer id) {
        this.id_inventario = id;
    }

}

