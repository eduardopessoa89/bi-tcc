package com.business.team.businessSaleProject.dto.simple;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioSimpleDTO {
	
    /**
    * id
    */
    private Long id;

    /**
 * Nome.
 */
 
private String nome;/**
 * Sobrenome.
 */
 
private String sobrenome;/**
 * Senha.
 */
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private String senha;/**
 * Email.
 */
 
private String email; 
	
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
 * Gets sobrenome
 * @return sobrenome
 */
public String getSobrenome() {
    return this.sobrenome;
}

/**
 * Sets sobrenome
 * @param sobrenome sobrenome
 */
public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
}
/**
 * Gets senha
 * @return senha
 */
public String getSenha() {
    return this.senha;
}

/**
 * Sets senha
 * @param senha senha
 */
public void setSenha(String senha) {
    this.senha = senha;
}
/**
 * Gets email
 * @return email
 */
public String getEmail() {
    return this.email;
}

/**
 * Sets email
 * @param email email
 */
public void setEmail(String email) {
    this.email = email;
}
 

}