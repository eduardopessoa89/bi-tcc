package com.business.team.businessSaleProject.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "itemvenda")
@Data
@SQLDelete(sql = "UPDATE itemvenda SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ItemVenda {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
* quantidade
*/
@Column(name = "quantidade")
private Double quantidade;
/**
* produto
*/
@OneToOne(cascade={CascadeType.ALL} )
@JoinColumn(name = "produto_id", referencedColumnName = "id", unique = true)
private Produto produto;


    @Column(name = "deleted", nullable = false)
private Boolean deleted = false;
}
