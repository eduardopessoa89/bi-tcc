package com.business.team.businessSaleProject.dto.simple;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PapelSimpleDTO {
	
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
 
private String descricao;  
	
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
  

}