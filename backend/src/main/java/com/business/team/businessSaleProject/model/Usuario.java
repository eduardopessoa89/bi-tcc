package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "usuario")
@Data
@SQLDelete(sql = "UPDATE usuario SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Usuario {

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
* sobrenome
*/
@Column(name = "sobrenome")
private String sobrenome;
/**
* senha
*/
@Column(name = "senha")
private String senha;
/**
* email
*/
@Column(name = "email")
private String email;
/**
* papeis
*/
@ManyToMany
@JoinTable(name = "usuario_papel",
        joinColumns = {@JoinColumn(name = "usuario_id")},
        inverseJoinColumns = {@JoinColumn(name = "papel_id")})
private List<Papel> papeis;


    @Column(name = "deleted", nullable = false)
private Boolean deleted = false;
}
