package com.bytecode.productos.servicios;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytecode.productos.entidad.Descontinuaciones;
import com.bytecode.productos.entidad.Productos;
import com.bytecode.productos.repositorios.ProductoRepositorio;

@Service
public class ServiciosProductosIMP implements ServiciosProductos{
	@Autowired 
	private ProductoRepositorio repositorio;

	@Override
	public List<Productos> listarProductos(String palabraClave) {
		if (palabraClave != null) {
			return repositorio.findAll(palabraClave);
		}
		return repositorio.findAll();
	}
	@Override
	public Productos ingresarProductos(Productos producto) {
		producto.setDescontinuado(Descontinuaciones.No);
		producto.setUnidadesEnOrden(0);
		return repositorio.save(producto);
	}
	@Override
	public Productos obtenerProductosID(Integer ID) {
		return repositorio.findById(ID).get();
	}
	@Override
	public Productos actualizarProductos(Productos productos) {
		return repositorio.save(productos);
	}
	@Override
	public void eliminarEstudiante(Integer ID) {
		repositorio.deleteById(ID);
	}
	@Override
	public ByteArrayInputStream exportarExcel() throws IOException {
		String[] columnas = {"ID", "Marca", "Nombre", "Cant.Unidad", "Prec.Unitario", "Uni.Almacen",
				"Uni.Orden", "Descontinuado"};
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Sheet sheet = workbook.createSheet("Productos");
		Row row = sheet.createRow(0);
		
		for (int i = 0; i < columnas.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnas[i]);
		}
		
		int initRow = 1;
		List<Productos> productos = listarProductos(null);
		for (Productos producto : productos) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(producto.getIDProducto());
			row.createCell(1).setCellValue(producto.getMarca());
			row.createCell(2).setCellValue(producto.getNombre());
			row.createCell(3).setCellValue(producto.getCantidadPorUnidad());
			row.createCell(4).setCellValue(producto.getPrecioUnitario());
			row.createCell(5).setCellValue(producto.getUnidadesEnAlmacen());
			row.createCell(6).setCellValue(producto.getUnidadesEnOrden());
			row.createCell(7).setCellValue(producto.getDescontinuado().toString());
			
			initRow++;
		}
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
}
