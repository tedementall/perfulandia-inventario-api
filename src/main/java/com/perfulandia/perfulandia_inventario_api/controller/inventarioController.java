package com.perfulandia.perfulandia_inventario_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.perfulandia_inventario_api.service.inventarioService;
import com.perfulandia.perfulandia_inventario_api.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



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

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<InventarioDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return Service.obtenerPorId(id)
            .map(dto -> {
                dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withSelfRel());
                dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hateoas")
    public List<InventarioDTO> obtenerTodosHATEOAS() {
        List<InventarioDTO> lista = Service.listar();

        for (InventarioDTO dto : lista) {
            
            dto.add(Link.of("http://localhost:8888/api/proxy/inventario").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }

    



    
    
}


