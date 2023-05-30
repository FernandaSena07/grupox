package com.fatec.sig1.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Exclusao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Nome é requerido")
	private int ongExcluidas;
	
	@NotBlank(message = "Nome é requerido")
	private int usuariosExcluidos;
	
	
	public Exclusao(int ongExcluidas,int usuariosExcluidos) {
		this.ongExcluidas = ongExcluidas;
		this.usuariosExcluidos = usuariosExcluidos;
	}
	
	
	public Exclusao() {
		this.setOngExcluidas(0);
		this.setUsuariosExcluidos(0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getOngExcluidas() {
		return ongExcluidas;
	}

	public void setOngExcluidas(int ongExcluidas) {
		this.ongExcluidas = ongExcluidas;
	}

	public int getUsuariosExcluidos() {
		return usuariosExcluidos;
	}

	public void setUsuariosExcluidos(int usuariosExcluidos) {
		this.usuariosExcluidos = usuariosExcluidos;
	}
	

}
