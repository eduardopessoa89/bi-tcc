package com.business.team.businessSaleProject.dto;

import com.business.team.businessSaleProject.dto.simple.PapelSimpleDTO;
import com.business.team.businessSaleProject.dto.simple.PermissaoSimpleDTO;
import lombok.Data;

@Data
public class PapelPermissaoDTO {

    /**
     * id
     */
    private Long id;

    /**
     * Permissao.
     */
    private PermissaoSimpleDTO permissao;
    /**
     * Papel.
     */
    private PapelSimpleDTO papel;

}
