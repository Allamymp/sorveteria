package org.ifpe.web2.sorveteria.model.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Sorvete implements Serializable {

    private int id;
    private LocalDate dataCompra;
    private TipoSorvete tipoSorvete;
    private List<Sabor> sabores = new ArrayList<>();


    public Sorvete(LocalDate dataCompra, TipoSorvete tipoSorvete, List<Sabor> sabores){
        this.dataCompra = dataCompra;
        this.tipoSorvete = tipoSorvete;
        this.sabores = sabores;
    }
}
