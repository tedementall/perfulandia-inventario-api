package com.perfulandia.perfulandia_inventario_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia_inventario_api.model.inventario;
import com.perfulandia.perfulandia_inventario_api.dto.*;
import com.perfulandia.perfulandia_inventario_api.repository.InventarioRepository;

@Service

public class inventarioService {

    @Autowired
    private InventarioRepository repository;

    public InventarioDTO crearInventario(InventarioDTO inventarioDTO) {
        inventario inventario = toEntity(inventarioDTO);
        inventario savedInventario = repository.save(inventario);
        return toDto(savedInventario);
    }

    public List<InventarioDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }
    
    public Optional <InventarioDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDto);
    }

    public Optional<InventarioDTO> actualizarInventario(Integer id, InventarioDTO inventarioDTO) {
        return repository.findById(id).map(inventario -> {
            inventario.setId_producto(inventarioDTO.getId_producto());
            inventario.setStock_disponible(inventarioDTO.getStock_disponible());
            inventario.setUbicacion(inventarioDTO.getUbicacion_bodega());
            return toDto(repository.save(inventario));
        }); 
    }


    //metodos auxiliares
    private InventarioDTO toDto(inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId_inventario(inventario.getId_inventario());
        dto.setId_producto(inventario.getId_producto());
        dto.setStock_disponible(inventario.getStock_disponible());
        dto.setUbicacion_bodega(inventario.getUbicacion());
        return dto;
    }

    private inventario toEntity(InventarioDTO dto) {
        inventario inventario = new inventario();
        inventario.setId_inventario(dto.getId_inventario());
        inventario.setId_producto(dto.getId_producto());
        inventario.setStock_disponible(dto.getStock_disponible());
        inventario.setUbicacion(dto.getUbicacion_bodega());
        return inventario;
    }


}
