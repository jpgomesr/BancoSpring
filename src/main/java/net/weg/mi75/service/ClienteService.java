package net.weg.mi75.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import net.weg.mi75.models.dto.*;
import net.weg.mi75.models.entity.*;
import net.weg.mi75.models.exceptions.MesmoTitularException;
import net.weg.mi75.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ContaService contaService;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public Cliente addCliente(@Valid ClientePostRequestDTO clienteDto) {
        Cliente cliente = clienteDto.convert();
        return repository.save(cliente);
    }

    public Cliente editCliente(@NotNull @Positive Integer id,
                               @Valid ClientePutRequestDTO clienteDto) {
        if (repository.existsById(id)) {
            Cliente clienteAtual = getClienteById(id);
            Cliente clienteEditado = clienteDto.convert();
            clienteEditado.setContas(clienteAtual.getContas());
            modelMapper.map(clienteEditado, clienteAtual);
            return repository.save(clienteAtual);
        }
        throw new NoSuchElementException();
    }

    @Transactional
    public Cliente changeContas(@NotNull @Positive Integer id, @NotNull @Positive Integer idConta) {
        Cliente cliente = repository.findById(id).orElseThrow(NoSuchElementException::new);
        Conta conta = contaService.getConta(idConta);
        if (cliente.getContas().contains(conta)) {
            cliente.rmConta(conta);
            conta.setTitular(null);
        } else if (conta.getTitular() == null) {
            cliente.addConta(conta);
            conta.setTitular(cliente);
        } else {
            throw new MesmoTitularException();
        }
        entityManager.flush();
        contaService.save(conta);
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
