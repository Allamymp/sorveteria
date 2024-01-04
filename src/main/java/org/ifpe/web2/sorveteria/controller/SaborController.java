package org.ifpe.web2.sorveteria.controller;


import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborUpdateDTO;
import org.ifpe.web2.sorveteria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/sabor")
public class SaborController {
    @Autowired
    SaborService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated SaborRequestDTO data) throws SQLException {
        service.create(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated SaborUpdateDTO data) throws SQLException {
        service.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Validated SaborIDDTO data) throws SQLException {
        service.delete(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws SQLException {
        return ResponseEntity.ok().body(service.readAll());
    }

    @PostMapping("/findById")
    public ResponseEntity<?> findById(@RequestBody @Validated SaborIDDTO data) throws SQLException {
        return ResponseEntity.ok().body(service.read(data));
    }

}
