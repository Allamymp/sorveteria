package org.ifpe.web2.sorveteria.service;

import org.ifpe.web2.sorveteria.model.entity.Sabor;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborUpdateDTO;
import org.ifpe.web2.sorveteria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SaborService {

    @Autowired
    SaborRepository repository;


    public void create(SaborRequestDTO a) throws SQLException {

        repository.create(new Sabor(a.nome(), a.descricao()));
    }

    public void update(SaborUpdateDTO data) throws SQLException {
        this.repository.update(new Sabor(
                data.id(),
                data.nome(),
                data.descricao()
        ));
    }

    public Sabor read(SaborIDDTO data) throws SQLException {
        return this.repository.read(data.id());
    }

    public void delete(SaborIDDTO data) throws SQLException {

        this.repository.delete(data.id());
    }

    public List<Sabor> readAll() throws SQLException {
        return this.repository.readAll();
    }
}
