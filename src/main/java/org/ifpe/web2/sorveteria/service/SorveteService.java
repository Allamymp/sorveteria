package org.ifpe.web2.sorveteria.service;

import org.ifpe.web2.sorveteria.model.entity.Sabor;
import org.ifpe.web2.sorveteria.model.entity.Sorvete;
import org.ifpe.web2.sorveteria.model.entity.dto.sorvete.SorveteIDDTO;
import org.ifpe.web2.sorveteria.repository.SorveteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SorveteService {

    @Autowired
    SorveteRepository repository;


    public void create(Sorvete sorvete) throws SQLException {

        repository.create(sorvete);
    }

    public Sorvete read(SorveteIDDTO data) throws SQLException {
        return this.repository.read(data.id());
    }

    public void delete(SorveteIDDTO data) throws SQLException {

        this.repository.delete(data.id());
    }

    public List<Sorvete> readAll() throws SQLException {
        return this.repository.readAll();
    }
}

