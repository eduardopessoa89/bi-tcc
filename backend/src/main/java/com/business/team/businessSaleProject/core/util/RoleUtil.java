package com.business.team.businessSaleProject.core.util;

    import com.business.team.businessSaleProject.dto.PapelDTO;
import com.business.team.businessSaleProject.dto.simple.PapelSimpleDTO;
import com.business.team.businessSaleProject.model.Papel; 
     import com.business.team.businessSaleProject.dto.PermissaoDTO;
import com.business.team.businessSaleProject.dto.simple.PermissaoSimpleDTO;
import com.business.team.businessSaleProject.model.Permissao;

import java.util.HashSet;
import java.util.Set;

public class RoleUtil {

        public static Set<PapelSimpleDTO> toPapeis(Set<Papel> papeis){
    Set<PapelSimpleDTO> newPapeis = new HashSet<>();
    papeis.forEach(papel -> {
        PapelSimpleDTO papelDTO = new PapelSimpleDTO();
        papelDTO.setId(papel.getId());
        papelDTO.setNome(papel.getNome());
        newPapeis.add(papelDTO);
    });
    return newPapeis;
} 

         public static Set<PermissaoDTO> toPermissaoDTOSet(Set<Permissao> permissoes) {
    Set<PermissaoDTO> newPermissoes = new HashSet<>();
    permissoes.forEach(permissao -> {
        PermissaoDTO perm = new PermissaoDTO();
        perm.setId(permissao.getId());
        perm.setNome(permissao.getNome());
        perm.setDescricao(permissao.getDescricao());
        newPermissoes.add(perm);
    });
    return newPermissoes;
}

}
