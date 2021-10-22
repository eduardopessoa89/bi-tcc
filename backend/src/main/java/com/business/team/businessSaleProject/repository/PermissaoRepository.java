package com.business.team.businessSaleProject.repository;

import com.business.team.businessSaleProject.core.repository.BaseRepository;
import com.business.team.businessSaleProject.model.Permissao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

public interface PermissaoRepository extends BaseRepository<Permissao, Long> {

     
      @Cacheable("bd-permissoes-papel")
@Query(value = "select p.nome from papel_permissao rp inner join permissao p on p.id = rp.permissao_id where rp.papel_id = ?1", nativeQuery = true)
List<String> getPermissoesByPapel(Long papelId);
     

}
