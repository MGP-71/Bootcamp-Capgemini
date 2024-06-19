package com.example;


import java.util.Optional;

public class Persona {
	private int id;
	private String nombre;
	private String apellido;
	
	public Persona(int id, String nombre, String apellido) {
		super();
		setNombre(nombre);
		setApellido(apellido);
		setId(id);
	}
	
	public Persona(int id, String nombre) {
		super();
		setNombre(nombre);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || "".equals(nombre.trim())) throw new IllegalArgumentException("Falta nombre");
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido == null) throw new IllegalArgumentException("Falta apellido");
		this.apellido = apellido;
	}
	
	public void clearApellidos() {
		this.apellido = null;
	}
	
	public boolean hasApellidos() {
		return apellido != null;
	}
	
	public Optional<String> getApellidos() {
		return Optional.ofNullable(apellido);
	}
}
