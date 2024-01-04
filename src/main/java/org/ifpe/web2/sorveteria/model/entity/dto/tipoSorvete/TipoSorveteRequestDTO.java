package org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete;

public record TipoSorveteRequestDTO(String tipo
        , Integer quantBolas
        , Double peso
        , String descricao
        , Double valor) {
}
