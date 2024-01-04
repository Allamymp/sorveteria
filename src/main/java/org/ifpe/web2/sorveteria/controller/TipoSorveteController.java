package org.ifpe.web2.sorveteria.controller;


import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteUpdateDTO;
import org.ifpe.web2.sorveteria.service.TipoSorveteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/tipoSorvete")
public class TipoSorveteController {
    @Autowired
    TipoSorveteService service;

    @CrossOrigin("*")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated TipoSorveteRequestDTO data) throws SQLException {
        service.create(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated TipoSorveteUpdateDTO data) throws SQLException {
        service.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Validated TipoSorveteIDDTO data) throws SQLException {
        service.delete(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws SQLException {
        return ResponseEntity.ok().body(service.readAll());
    }

    @PostMapping("/findById")
    public ResponseEntity<?> findById(@RequestBody @Validated TipoSorveteIDDTO data) throws SQLException {
        return ResponseEntity.ok().body(service.read(data));
    }
}

