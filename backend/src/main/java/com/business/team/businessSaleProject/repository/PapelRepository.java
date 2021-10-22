package com.business.team.businessSaleProject.repository;

import com.business.team.businessSaleProject.core.repository.BaseRepository;
import com.business.team.businessSaleProject.model.Papel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

public interface PapelRepository extends BaseRepository<Papel, Long> {

     
        
       @Cacheable("db-user-roles")
@Query(value = "select r.* from usuario_papel up inner join papel r on r.id = up.papel_id where up.usuario_id = ?1", nativeQuery = true)
Set<Papel> getPapeis(Long usuarioId);

}
