package net.weg.mi75.service;

import jakarta.validation.Valid;
import lombok.*;
import net.weg.mi75.models.dto.*;
import net.weg.mi75.models.entity.*;
import net.weg.mi75.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContaService {
    @NonNull
    private final ContaRepository repository;
    @Autowired
    @Lazy
    private ClienteService clienteService;

    public Conta addConta(@Valid ContaPostRequestDTO contaDTO) {
        Cliente cliente = clienteService.getClienteById(contaDTO.idTitular());
        Conta conta = contaDTO.convert(cliente);
        return repository.save(conta);
    }

    public Conta getConta(Integer id) {
        return repository.findById(id).get();
    }

    public List<Conta> getAllContas() {
        return repository.findAll();
    }

    public Page<Conta> getAllContas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Map<String, String> deleteConta(Integer id) {
        Map<String, String> response = new HashMap<>();
        repository.delete(repository.findById(id).get());
        response.put("message", "Conta deletada com sucesso!");
        return response;
    }

    public Conta updateConta(Integer id, ContaPutRequestDTO contaDto) {
        Conta contaAntiga = getConta(id);
        contaAntiga.setNumero(contaDto.numero());
        contaAntiga.setLimite(contaDto.limite());
        return repository.save(contaAntiga);
    }

    public Conta changeLimite(Integer id, Double limite) {
        Conta conta = getConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }

    public void save(Conta conta) {
        repository.save(conta);
    }
}
