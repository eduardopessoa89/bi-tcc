package com.business.team.businessSaleProject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "papel_permissao")
public class PapelPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "papel_id")
    private Papel papel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permissao_id")
    private Permissao permissao;
}
