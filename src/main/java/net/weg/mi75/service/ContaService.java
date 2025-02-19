package net.weg.mi75.service;

import net.weg.mi75.models.dto.ContaDTO;
import net.weg.mi75.models.entity.Conta;
import net.weg.mi75.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContaService {
    private final ContaRepository repository;

    @Autowired
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Map<String, String>> addConta(ContaDTO contaDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            Conta contaSalva = repository.save(contaDTO.convert());
            response.put("message", "Conta criada com sucesso!");
            response.put("conta", contaSalva.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("error", "Erro ao criar conta!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Conta> getConta(Integer id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Conta>> getAllContas() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Page<Conta>> getAllContas(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> deleteConta(Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            repository.delete(repository.findById(id).get());
            response.put("message", "Conta deletada com sucesso!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Conta não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<Conta> updateConta(Integer id, Conta conta) {
        conta.setId(id);
        return ResponseEntity.ok(repository.save(conta));
    }

    public ResponseEntity<Conta> changeLimite(Integer id, Double limite) {
        Conta conta = getConta(id).getBody();
        conta.setLimite(limite);
        return ResponseEntity.ok(repository.save(conta));
    }
}
