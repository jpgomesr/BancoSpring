package net.weg.mi75.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import net.weg.mi75.models.dto.ClientePostRequestDTO;
import net.weg.mi75.models.dto.ClientePutRequestDTO;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.models.entity.Conta;
import net.weg.mi75.models.exceptions.MesmoTitularException;
import net.weg.mi75.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ContaService contaService;


    public Cliente addCliente(@Valid ClientePostRequestDTO clienteDto) {
        Cliente cliente = clienteDto.convert();
        return repository.save(cliente);
    }

    public Cliente editCliente(@NotNull @Positive Integer id, @Valid ClientePutRequestDTO clienteDto) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteDto.convert();
        cliente.setId(id);
        return repository.save(cliente);
    }

    public Cliente changeContas(@NotNull @Positive Integer id, @NotNull @Positive Integer idConta) {
        Cliente cliente = repository.findById(id).get();
        Conta conta = contaService.getConta(idConta);
        if (cliente.getContas().contains(conta)) {
            cliente.rmConta(conta);
        } else if (conta.getTitular() == null) {
            cliente.addConta(conta);
        } else {
            throw new MesmoTitularException();
        }
        return repository.save(cliente);
    }

    public Cliente getClienteById(@NotNull @Positive Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Nenhuma conta encontrada!");
        });
    }

    public Page<Cliente> getClientes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteCliente(@NotNull @Positive Integer id) {
        repository.delete(repository.findById(id).get());
    }
}
