package com.business.team.businessSaleProject.dto.simple;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemVendaSimpleDTO {
	
    /**
    * id
    */
    private Long id;

    /**
 * Quantidade.
 */
 
private Double quantidade; 
	
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
 * Gets quantidade
 * @return quantidade
 */
public Double getQuantidade() {
    return this.quantidade;
}

/**
 * Sets quantidade
 * @param quantidade quantidade
 */
public void setQuantidade(Double quantidade) {
    this.quantidade = quantidade;
}
 

}