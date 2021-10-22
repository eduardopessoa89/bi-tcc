package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "papel")
@Data
@SQLDelete(sql = "UPDATE papel SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Papel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
* nome
*/
@Column(name = "nome")
private String nome;
/**
* descricao
*/
@Column(name = "descricao")
private String descricao;
/**
* permissoes
*/
@ManyToMany
@JoinTable(name = "papel_permissao",
        joinColumns = {@JoinColumn(name = "papel_id")},
        inverseJoinColumns = {@JoinColumn(name = "permissao_id")})
private List<Permissao> permissoes;
/**
* usuarios
*/
@ManyToMany
@JoinTable(name = "usuario_papel",
        joinColumns = {@JoinColumn(name = "papel_id")},
        inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
private List<Usuario> usuarios;


     
}
