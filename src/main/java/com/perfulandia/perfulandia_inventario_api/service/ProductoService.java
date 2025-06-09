package com.perfulandia.perfulandia_inventario_api.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.perfulandia.perfulandia_inventario_api.dto.ProductoDTO;
import com.perfulandia.perfulandia_inventario_api.model.Producto;
import com.perfulandia.perfulandia_inventario_api.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = toEntity(productoDTO);
        Producto savedProducto = repository.save(producto);
        return toDto(savedProducto);
    }
    
    public List<ProductoDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDto);
    }

    public Optional<ProductoDTO> actualizarProducto(Integer id, ProductoDTO productoDTO) {
        return repository.findById(id).map(producto -> {
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setPrecio_unitario(productoDTO.getPrecio_unitario());
            producto.setCategoria(productoDTO.getCategoria());
            producto.setActivo(productoDTO.isActivo());
            return toDto(repository.save(producto));
        });
    }

    public boolean eliminarProducto(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    

    

    //metodos auxiliares
    private ProductoDTO toDto(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId_producto(producto.getId_producto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio_unitario(producto.getPrecio_unitario());
        dto.setCategoria(producto.getCategoria());
        dto.setActivo(producto.isActivo());
        return dto;
    }
        private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId_producto(dto.getId_producto());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio_unitario(dto.getPrecio_unitario());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.isActivo());
        return producto;
    }
}
