package net.weg.mi75.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.mi75.models.dto.ContaPostRequestDTO;
import net.weg.mi75.models.dto.ContaPutRequestDTO;
import net.weg.mi75.models.entity.Conta;
import net.weg.mi75.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContaService {
    private final ContaRepository repository;

    public Conta addConta(@Valid ContaPostRequestDTO contaDTO) {
        Conta conta = contaDTO.convert();
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
        Conta conta = contaDto.convert();
        conta.setId(id);
        return repository.save(conta);
    }

    public Conta changeLimite(Integer id, Double limite) {
        Conta conta = getConta(id);
        conta.setLimite(limite);
        return repository.save(conta);
    }
}
