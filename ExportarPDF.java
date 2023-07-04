package com.bytecode.productos.servicios;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.bytecode.productos.entidad.Productos;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;

import jakarta.servlet.http.HttpServletResponse;

public class ExportarPDF{
	
	private List<Productos> productos;
	
	public ExportarPDF(List<Productos> productos) {
		super();
		this.setProductos(productos);
	}

	private void escribirCabecera(PdfPTable tabla) {
		// TODO Auto-generated method stub
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(7);
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);
        
        cell.setPhrase(new Phrase("Nombre", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Marca", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad por Unidad", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Precio Unitario", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Unidades en Almacen", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Unidades en Orden", fuente));
        tabla.addCell(cell);
        cell.setPhrase(new Phrase("Descontinuado", fuente));
        tabla.addCell(cell);
	}
	
	private void escribirInformacion(PdfPTable tabla) {
		for (Productos producto : productos) {
			tabla.addCell(producto.getNombre());
			tabla.addCell(producto.getMarca());
			tabla.addCell(producto.getCantidadPorUnidad());
			tabla.addCell(String.valueOf(producto.getPrecioUnitario()));
			tabla.addCell(String.valueOf(producto.getUnidadesEnAlmacen()));
			tabla.addCell(String.valueOf(producto.getUnidadesEnOrden()));
			tabla.addCell(String.valueOf(producto.getDescontinuado()));
		}
	}
	
	public void exportarPDF(HttpServletResponse respuesta) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, respuesta.getOutputStream());
		
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setSize(18);
		fuente.setColor(Color.BLACK);
		
		Paragraph parrafo = new Paragraph("Lista de Productos", fuente);
		parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		documento.add(parrafo);
		
		PdfPTable tabla = new PdfPTable(7);
		tabla.setWidthPercentage(100f);
		tabla.setSpacingBefore(10);
		
		escribirCabecera(tabla);
		escribirInformacion(tabla);
		
		documento.add(tabla);
		documento.close();
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}
}
