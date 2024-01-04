package org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete;

public record TipoSorveteUpdateDTO(Integer id
        , String tipo
        , Integer quantBolas
        , Double peso
        , String descricao
        , Double valor) {
}