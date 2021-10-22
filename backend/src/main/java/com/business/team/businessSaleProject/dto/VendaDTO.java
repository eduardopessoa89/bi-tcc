package com.business.team.businessSaleProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.business.team.businessSaleProject.dto.simple.*;
import java.util.List;

public class VendaDTO {

    /**
    * id
    */
    private Long id;
    
    /**
 * DataVenda.
 */
 
private java.util.Date dataVenda;/**
 * Cliente.
 */
 
private String cliente;/**
 * ItensVenda.
 */
private List<ItemVendaSimpleDTO> itensVenda;
	
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
 * Gets dataVenda
 * @return dataVenda
 */
public java.util.Date getDataVenda() {
    return this.dataVenda;
}

/**
 * Sets dataVenda
 * @param dataVenda dataVenda
 */
public void setDataVenda(java.util.Date dataVenda) {
    this.dataVenda = dataVenda;
}
/**
 * Gets cliente
 * @return cliente
 */
public String getCliente() {
    return this.cliente;
}

/**
 * Sets cliente
 * @param cliente cliente
 */
public void setCliente(String cliente) {
    this.cliente = cliente;
}
/**
 * Gets itensVenda
 * @return itensVenda
 */
public List<ItemVendaSimpleDTO> getItensVenda() {
    return this.itensVenda;
}

/**
 * Sets itensVenda
 * @param itensVenda itensVenda
 */
public void setItensVenda(List<ItemVendaSimpleDTO> itensVenda) {
    this.itensVenda = itensVenda;
}


}
