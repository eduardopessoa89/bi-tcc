package com.business.team.businessSaleProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.business.team.businessSaleProject.dto.simple.*;
import java.util.List;

public class ProdutoDTO {

    /**
    * id
    */
    private Long id;
    
    /**
 * Valor.
 */
 
private Double valor;/**
 * Categoria.
 */
 
private String categoria;/**
 * Nome.
 */
 
private String nome;
	
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
 * Gets valor
 * @return valor
 */
public Double getValor() {
    return this.valor;
}

/**
 * Sets valor
 * @param valor valor
 */
public void setValor(Double valor) {
    this.valor = valor;
}
/**
 * Gets categoria
 * @return categoria
 */
public String getCategoria() {
    return this.categoria;
}

/**
 * Sets categoria
 * @param categoria categoria
 */
public void setCategoria(String categoria) {
    this.categoria = categoria;
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


}
