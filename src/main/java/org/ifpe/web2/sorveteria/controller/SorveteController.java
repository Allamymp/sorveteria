package org.ifpe.web2.sorveteria.controller;

import org.ifpe.web2.sorveteria.model.entity.Sabor;
import org.ifpe.web2.sorveteria.model.entity.Sorvete;
import org.ifpe.web2.sorveteria.model.entity.TipoSorvete;
import org.ifpe.web2.sorveteria.model.entity.dto.report.ReportRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sabor.SaborIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sorvete.SorveteIDDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.sorvete.SorveteRequestDTO;
import org.ifpe.web2.sorveteria.model.entity.dto.tipoSorvete.TipoSorveteIDDTO;
import org.ifpe.web2.sorveteria.service.SaborService;
import org.ifpe.web2.sorveteria.service.SorveteService;
import org.ifpe.web2.sorveteria.service.TipoSorveteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sorvete")
public class SorveteController {
    @Autowired
    SorveteService service;
    @Autowired
    TipoSorveteService tipoSorveteService;
    @Autowired
    SaborService saborService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody@Validated SorveteRequestDTO data) throws SQLException {
        TipoSorvete tipoSorvete = tipoSorveteService.read( new TipoSorveteIDDTO(data.tipoSorveteId()));
        List<Integer> saborListId = data.saborListId();
        List<Sabor>  saborList = new ArrayList<>();
        for(Integer id : saborListId){
            if(saborService.read(new SaborIDDTO(id))!=null){
                saborList.add(saborService.read(new SaborIDDTO(id)));
            }
        }
        service.create(new Sorvete(LocalDate.now(),tipoSorvete,saborList));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody@Validated SorveteIDDTO data) throws SQLException {
        service.delete(data);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() throws SQLException {
        return ResponseEntity.ok().body(service.readAll());
    }
    @PostMapping("/findById")
    public ResponseEntity<?> findById(@RequestBody@Validated SorveteIDDTO data) throws SQLException {
        return ResponseEntity.ok().body(service.read(data));
    }
    @PostMapping("/report")
    public ResponseEntity<?> report(@RequestBody@Validated ReportRequestDTO id) throws SQLException {
        return ResponseEntity.ok().body(service.readAllByDate(id.date()));
    }
}
