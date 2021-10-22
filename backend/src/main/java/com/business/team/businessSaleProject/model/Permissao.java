package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "permissao")
@Data
@SQLDelete(sql = "UPDATE permissao SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Permissao {

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
* papeis
*/
@ManyToMany
@JoinTable(name = "papel_permissao",
        joinColumns = {@JoinColumn(name = "permissao_id")},
        inverseJoinColumns = {@JoinColumn(name = "papel_id")})
private List<Papel> papeis;


     
}
