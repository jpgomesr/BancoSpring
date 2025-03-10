package net.weg.mi75.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_pj")
public class PJuridica extends Cliente {
    private Integer id;
    private Long cnpj;
    private String razaoSocial;
}
