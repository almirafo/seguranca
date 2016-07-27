package com.mictmr.seguranca.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UsuarioRest {

	Long idUsuario;

	String nome;
	
	String email;
	
	String status;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
