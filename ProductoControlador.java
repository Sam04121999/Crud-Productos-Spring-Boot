package com.bytecode.productos.controlador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bytecode.productos.servicios.ServiciosProductos;
import com.bytecode.productos.servicios.ExportarPDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

import com.bytecode.productos.entidad.Productos;

@Controller
public class ProductoControlador {
	@Autowired
	private ServiciosProductos servicios;

	@GetMapping({ "/productos", "/" })
	public String listarEstudiantes(Model modelo, @Param("palabraClave") String palabraClave) {
		List<Productos> listarProductos = servicios.listarProductos(palabraClave);
		modelo.addAttribute("productos", listarProductos);
		modelo.addAttribute("palabraClave", palabraClave);
		return "index"; // nos retorna al archivo productos
	}
	

	@GetMapping("/productos/nuevo")
	public String mostrarFormularioProducto(Model modelo) {
		Productos producto = new Productos();
		modelo.addAttribute("producto", producto);
		return "insertar_productos"; // nos retorna al archivo insertar_productos
	}

	@PostMapping("/productos")
	public String guardarProducto(@ModelAttribute("producto") Productos producto) {
		servicios.ingresarProductos(producto);
		return "redirect:/productos?exito_ingresar";
	}

	@GetMapping("/productos/actualizar/{ID}")
	public String mostrarFormularioEditar(@PathVariable Integer ID, Model modelo) {
		modelo.addAttribute("producto", servicios.obtenerProductosID(ID));
		return "editar_producto";
	}

	@PostMapping("/productos/{ID}")
	public String actualizarProducto(@PathVariable Integer ID, @ModelAttribute("producto") Productos producto,
			Model modelo) {
		Productos productoExistente = servicios.obtenerProductosID(ID);
		if (producto.getUnidadesEnOrden() > producto.getUnidadesEnAlmacen()) {
			return "redirect:/productos/actualizar/{ID}?error_cantidad";
		}
		productoExistente.setNombre(producto.getNombre());
		productoExistente.setMarca(producto.getMarca());
		productoExistente.setCantidadPorUnidad(producto.getCantidadPorUnidad());
		productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
		productoExistente.setUnidadesEnOrden(producto.getUnidadesEnOrden());
		productoExistente.setDescontinuado(producto.getDescontinuado());

		servicios.actualizarProductos(productoExistente);
		return "redirect:/productos?exito_actualizar";
	}
	
	@GetMapping("/exportar/todo/excel")
	public ResponseEntity<InputStreamResource> exportarExcel() throws Exception {
		ByteArrayInputStream stream = servicios.exportarExcel();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; " + "filename=productos.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	@GetMapping("/exportar/todo/PDF")
	public void exportarPDF(HttpServletResponse respuesta) throws DocumentException, IOException {
		respuesta.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=productos.pdf";
		respuesta.setHeader(headerKey, headerValue);
		
		List<Productos> productos = servicios.listarProductos(null);
		
		ExportarPDF exportador = new ExportarPDF(productos);
		exportador.exportarPDF(respuesta);
	}

	@GetMapping("/productos/{ID}")
	public String eliminarProducto(@PathVariable Integer ID) {
		servicios.eliminarEstudiante(ID);
		return "redirect:/productos?exito_eliminar";
	}
}
