package net.weg.mi75.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_pf")
public class PFisica extends Cliente {
    private Integer id;
    private String sobrenome;
    private Long rg;
}
