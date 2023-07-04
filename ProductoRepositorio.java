package com.bytecode.productos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bytecode.productos.entidad.Productos;

public interface ProductoRepositorio extends JpaRepository<Productos, Integer>{
	@Query("SELECT p FROM Productos p WHERE p.Nombre LIKE %?1%"
			+ "OR p.CantidadPorUnidad LIKE %?1%"
			+ "OR p.Marca LIKE %?1%")
	public List<Productos> findAll(String palabraClave);
}
