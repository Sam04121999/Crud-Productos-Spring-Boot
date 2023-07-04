package com.bytecode.productos.servicios;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.bytecode.productos.entidad.Productos;

public interface ServiciosProductos {
	public List<Productos> listarProductos(String palabraClave);
	public Productos ingresarProductos(Productos producto);
	public Productos obtenerProductosID(Integer ID);
	public Productos actualizarProductos(Productos productos);
	public void eliminarEstudiante(Integer ID);
	public ByteArrayInputStream exportarExcel() throws IOException;
}
