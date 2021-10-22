package com.business.team.businessSaleProject.core.dto;

import com.business.team.businessSaleProject.dto.simple.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserAuthenticateDTO {

    /**
     * ID.
     */
    private Long id;

    /**
 * Nome.
 */
 
private String nome;/**
 * Sobrenome.
 */
 
private String sobrenome; /**
 * Email.
 */
 
private String email;/**
 * Papeis.
 */
private Set<PapelSimpleDTO> papeis;

    

    /**
     * Token.
     */
    private String token;

    /**
     * Refresh token.
     */
    private String refreshToken;

         /**
  * Permissoes.
  */
private List<String> Permissoes;

    /**
     * Construtor.
     */
    public UserAuthenticateDTO() {
    }

    /**
     * Construtor.
     *
     * @param id
     * @param login
     */
       public UserAuthenticateDTO(Long id, String email) {
    super();
    this.id = id;
    this.email = email;
} 

}
