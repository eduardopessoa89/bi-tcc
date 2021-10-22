package com.business.team.businessSaleProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.business.team.businessSaleProject.dto.simple.*;
import java.util.List;

public class PapelDTO {

    /**
    * id
    */
    private Long id;
    
    /**
 * Nome.
 */
 
private String nome;/**
 * Descricao.
 */
 
private String descricao;/**
 * Permissoes.
 */
private List<PermissaoSimpleDTO> permissoes;/**
 * Usuarios.
 */
private List<UsuarioSimpleDTO> usuarios;
	
	/**
	* Gets id
	* @return id
	*/
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets id
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

    /**
 * Gets nome
 * @return nome
 */
public String getNome() {
    return this.nome;
}

/**
 * Sets nome
 * @param nome nome
 */
public void setNome(String nome) {
    this.nome = nome;
}
/**
 * Gets descricao
 * @return descricao
 */
public String getDescricao() {
    return this.descricao;
}

/**
 * Sets descricao
 * @param descricao descricao
 */
public void setDescricao(String descricao) {
    this.descricao = descricao;
}
/**
 * Gets permissoes
 * @return permissoes
 */
public List<PermissaoSimpleDTO> getPermissoes() {
    return this.permissoes;
}

/**
 * Sets permissoes
 * @param permissoes permissoes
 */
public void setPermissoes(List<PermissaoSimpleDTO> permissoes) {
    this.permissoes = permissoes;
}
/**
 * Gets usuarios
 * @return usuarios
 */
public List<UsuarioSimpleDTO> getUsuarios() {
    return this.usuarios;
}

/**
 * Sets usuarios
 * @param usuarios usuarios
 */
public void setUsuarios(List<UsuarioSimpleDTO> usuarios) {
    this.usuarios = usuarios;
}


}
