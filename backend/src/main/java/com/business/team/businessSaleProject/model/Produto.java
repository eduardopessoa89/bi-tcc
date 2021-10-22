package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "produto")
@Data
@SQLDelete(sql = "UPDATE produto SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
* valor
*/
@Column(name = "valor")
private Double valor;
/**
* categoria
*/
@Column(name = "categoria")
private String categoria;
/**
* nome
*/
@Column(name = "nome")
private String nome;


    @Column(name = "deleted", nullable = false)
private Boolean deleted = false;
}
