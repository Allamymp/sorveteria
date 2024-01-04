package org.ifpe.web2.sorveteria.model.entity;


import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Sabor implements Serializable {
    private Integer id;
    private String nome;
    private String descricao;

    public Sabor(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }


}
