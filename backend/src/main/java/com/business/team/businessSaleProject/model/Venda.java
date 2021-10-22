package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "venda")
@Data
@SQLDelete(sql = "UPDATE venda SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Venda {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
* dataVenda
*/
@Column(name = "dataVenda")
private java.util.Date dataVenda;
/**
* cliente
*/
@Column(name = "cliente")
private String cliente;
/**
* itensVenda
*/
@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
@JoinColumn(name = "venda_id", referencedColumnName = "id")
private List<ItemVenda> itensVenda;


    @Column(name = "deleted", nullable = false)
private Boolean deleted = false;
}
