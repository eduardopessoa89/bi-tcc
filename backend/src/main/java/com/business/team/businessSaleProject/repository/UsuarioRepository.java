package com.business.team.businessSaleProject.repository;

import com.business.team.businessSaleProject.core.repository.BaseRepository;
import com.business.team.businessSaleProject.model.Usuario;

import org.springframework.data.jpa.repository.Query;
 

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query(value = "select * from usuario where upper(   email ) = upper(?1) and   senha   = ?2 and deleted = false", nativeQuery = true)
Usuario login(   String email ,   String senha  );

   Usuario findByEmail(String email); 
         
     

}
