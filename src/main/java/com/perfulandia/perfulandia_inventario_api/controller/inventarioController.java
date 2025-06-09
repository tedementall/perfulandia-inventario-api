package com.perfulandia.perfulandia_inventario_api.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import com.perfulandia.perfulandia_inventario_api.service.inventarioService;
import com.perfulandia.perfulandia_inventario_api.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;



@RestController
@RequestMapping("/api/inventario")

public class inventarioController {
    @Autowired
    private inventarioService Service;

    @PostMapping
    public ResponseEntity<InventarioDTO> crearInventario(@RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO nuevoInventario = Service.crearInventario(inventarioDTO);
        return ResponseEntity.ok(nuevoInventario);
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        List<InventarioDTO> inventarios = Service.listar();
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> obtenerPorId(@RequestParam Integer id) {
        return Service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(@RequestParam Integer id, @RequestBody InventarioDTO inventarioDTO) {
        return Service.actualizarInventario(id, inventarioDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    

    
    
}


