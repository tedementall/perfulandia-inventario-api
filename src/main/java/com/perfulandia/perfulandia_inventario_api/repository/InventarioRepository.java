package com.perfulandia.perfulandia_inventario_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia.perfulandia_inventario_api.model.inventario;

public interface InventarioRepository extends JpaRepository<inventario, Integer> {
	
}
