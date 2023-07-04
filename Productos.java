package com.bytecode.productos.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IDProducto;

	@Column(name = "NombreProducto", nullable = true, length = 30)
	private String Nombre;
	
	@Column(name = "Marca", nullable = true, length = 30)
	private String Marca;
	
	@Column(name = "CantidadPorUnidad", nullable = true, length = 30)
	private String CantidadPorUnidad;
	
	@Column(name = "PrecioUnitario", nullable = true)
	private Double PrecioUnitario;
	
	@Column(name = "UnidadesEnAlmacen", nullable = false, length = 10, columnDefinition = "int default 0")
	private Integer UnidadesEnAlmacen;
	
	@Column(name = "UnidadesEnOrden", nullable = false, length = 10, columnDefinition = "int default 0")
	private Integer UnidadesEnOrden;
	
	@Column(name = "Descontinuado", nullable = false, columnDefinition = "varchar(2) default 'No'")
	@Enumerated(EnumType.STRING)
	private Descontinuaciones Descontinuado;
	
	public Productos(Integer iDProducto, String nombre, String marca, String cantidadPorUnidad,
			Double precioUnitario, Integer unidadesEnAlmacen, Integer unidadesEnOrden,
			Descontinuaciones descontinuado) {
		super();
		IDProducto = iDProducto;
		Nombre = nombre;
		Marca = marca;
		CantidadPorUnidad = cantidadPorUnidad;
		PrecioUnitario = precioUnitario;
		UnidadesEnAlmacen = unidadesEnAlmacen;
		UnidadesEnOrden = unidadesEnOrden;
		Descontinuado = descontinuado;
	}
	
	public Productos(String nombre, String marca, String cantidadPorUnidad,
			Double precioUnitario, Integer unidadesEnAlmacen, Integer unidadesEnOrden,
			Descontinuaciones descontinuado) {
		super();
		Nombre = nombre;
		Marca = marca;
		CantidadPorUnidad = cantidadPorUnidad;
		PrecioUnitario = precioUnitario;
		UnidadesEnAlmacen = unidadesEnAlmacen;
		UnidadesEnOrden = unidadesEnOrden;
		Descontinuado = descontinuado;
	}
	
	
	public Productos() {
		super();
	}
	
	public Integer getIDProducto() {
		return IDProducto;
	}

	public void setIDProducto(Integer iDProducto) {
		this.IDProducto = iDProducto;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}


	public String getCantidadPorUnidad() {
		return CantidadPorUnidad;
	}

	public void setCantidadPorUnidad(String cantidadPorUnidad) {
		this.CantidadPorUnidad = cantidadPorUnidad;
	}

	public Double getPrecioUnitario() {
		return PrecioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.PrecioUnitario = precioUnitario;
	}

	public Integer getUnidadesEnAlmacen() {
		return UnidadesEnAlmacen;
	}

	public void setUnidadesEnAlmacen(Integer unidadesEnAlmacen) {
		this.UnidadesEnAlmacen = unidadesEnAlmacen;
	}

	public Integer getUnidadesEnOrden() {
		return UnidadesEnOrden;
	}

	public void setUnidadesEnOrden(Integer unidadesEnOrden) {
		this.UnidadesEnOrden = unidadesEnOrden;
	}

	public Descontinuaciones getDescontinuado() {
		return Descontinuado;
	}

	public void setDescontinuado(Descontinuaciones descontinuado) {
		Descontinuado = descontinuado;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}
	
	
}
