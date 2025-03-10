package net.weg.mi75.models.dto;

import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.models.entity.Conta;

import java.util.List;

public record ClientePutRequestDTO(
        String nome,
        Long cpf,
        List<Conta> contas
) {
    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).contas(this.contas).build();
    }
}
