package org.ifpe.web2.sorveteria.service;

import org.ifpe.web2.sorveteria.model.entity.TipoSorvete;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteUpdateDTO;
import org.ifpe.web2.sorveteria.repository.TipoSorveteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TipoSorveteService {
    @Autowired
    TipoSorveteRepository repository;


    public void create(TipoSorveteRequestDTO data) throws SQLException {

        repository.create(new TipoSorvete(
                data.tipo(),
                data.quantBolas(),
                data.peso(),
                data.descricao(),
                data.valor()
        ));
    }

    public void update(TipoSorveteUpdateDTO data) throws SQLException {
        this.repository.update(new TipoSorvete(
                data.id(),
                data.tipo(),
                data.quantBolas(),
                data.peso(),
                data.descricao(),
                data.valor()));
    }

    public TipoSorvete read(TipoSorveteIDDTO data) throws SQLException {
        return this.repository.read(data.id());
    }

    public void delete(TipoSorveteIDDTO data) throws SQLException {

        this.repository.delete(data.id());
    }

    public List<TipoSorvete> readAll() throws SQLException {
        return this.repository.readAll();
    }
}

