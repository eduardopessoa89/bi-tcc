package com.business.team.businessSaleProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.business.team.businessSaleProject.dto.simple.*;
import java.util.List;

public class ItemVendaDTO {

    /**
    * id
    */
    private Long id;
    
    /**
 * Quantidade.
 */
 
private Double quantidade;/**
 * Produto.
 */
private ProdutoSimpleDTO produto;

	
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
/**
 * Gets produto
 * @return produto
 */
public ProdutoSimpleDTO getProduto() {
    return this.produto;
}

/**
 * Sets produto
 * @param produto produto
 */
public void setProduto(ProdutoSimpleDTO produto) {
    this.produto = produto;
}


}
