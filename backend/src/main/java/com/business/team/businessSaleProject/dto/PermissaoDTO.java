package com.business.team.businessSaleProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.business.team.businessSaleProject.dto.simple.*;
import java.util.List;

public class PermissaoDTO {

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
 * Papeis.
 */
private List<PapelSimpleDTO> papeis;
	
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
 * Gets papeis
 * @return papeis
 */
public List<PapelSimpleDTO> getPapeis() {
    return this.papeis;
}

/**
 * Sets papeis
 * @param papeis papeis
 */
public void setPapeis(List<PapelSimpleDTO> papeis) {
    this.papeis = papeis;
}


}
