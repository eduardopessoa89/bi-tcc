package com.business.team.businessSaleProject.repository;

import com.business.team.businessSaleProject.core.repository.BaseRepository;
import com.business.team.businessSaleProject.model.PapelPermissao;
import org.springframework.data.jpa.repository.Query;

public interface PapelPermissaoRepository extends BaseRepository<PapelPermissao, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM papel_permissao WHERE papel_id = ?1 and permissao_id = ?2")
    PapelPermissao findPapelPermissaoByPapelAndPermissao(Long papelId, Long permissaoId);

}
