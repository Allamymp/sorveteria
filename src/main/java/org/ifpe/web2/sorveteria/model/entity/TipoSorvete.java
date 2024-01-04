package org.ifpe.web2.sorveteria.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TipoSorvete {
    private Integer id;
    private String tipo;
    private Integer quantBolas;
    private Double peso;
    private String descricao;
    private Double valor;

    public TipoSorvete(String tipo, Integer quantBolas, Double peso, String descricao, Double valor){
        this.tipo = tipo;
        this.quantBolas = quantBolas;
        this.peso = peso;
        this.descricao = descricao;
        this.valor = valor;
    }
}
