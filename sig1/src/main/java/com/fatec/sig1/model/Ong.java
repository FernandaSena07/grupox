package com.fatec.sig1.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;

//The JPA was renamed as Jakarta Persistence in 2019 and version 3.0 was released in 2020. This included the renaming of packages and properties
//from javax. persistence to jakarta. persistence.
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ong {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Nome é requerido")
	private String nome;
	
	@NotBlank(message = "Insira apenas números")
	private int telefone;
	
	@CNPJ
	@NotBlank(message = "O CNPJ é obrigatório")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cnpj;
	
	@NotBlank(message = "O CEP é obrigatório.")
	private String cep;
	
	private String endereco;
	@NotBlank(message = "O complemento deve ser informado")
	
	private String complemento;
	private String descricao;
	private String segmento;
	
	@Pattern(regexp = "/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i", message = "O e-mail deve ser escrito no formato nome@gmail.com")
	@NotBlank(message = "O Email é obrigatório")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String email;

	@NotBlank(message = "A senha é obrigatório")
	private String senha;
	
	
	public Ong(String nome, int telefone, String cep, String complemento, String descricao, String segmento, String email, String senha, @CNPJ @NotBlank(message = "O CNPJ é obrigatório") String cnpj) {
		this.nome = nome;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.cep = cep;
		this.complemento = complemento;
		this.descricao = descricao;
		this.segmento = segmento;
		this.email = email;
		this.senha = senha;
	}

	public Ong() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	
}
	
	
