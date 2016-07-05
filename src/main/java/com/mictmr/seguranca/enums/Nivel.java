package com.mictmr.seguranca.enums;

public enum Nivel {
	USUARIO("Usuário"),
	ADMINISTRADOR("Administrador"),
	CONVIDADO("Convidado");
	
	private String descricao;
	
	Nivel(String descricao){
		this.descricao= descricao;
	}
	
	
	public String getDescricao(){
		return this.descricao;
	}
}
