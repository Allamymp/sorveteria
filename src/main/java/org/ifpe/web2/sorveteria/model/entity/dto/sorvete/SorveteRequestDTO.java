package org.ifpe.web2.sorveteria.model.entity.dto.sorvete;

import java.util.List;

public record SorveteRequestDTO(Integer tipoSorveteId, List<Integer> saborListId) {
}
