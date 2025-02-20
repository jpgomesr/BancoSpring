package net.weg.mi75.service;

import net.weg.mi75.models.dto.ClienteDTO;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService {
    ClienteRepository repository;

    public ResponseEntity<Map<String, String>> addCliente(ClienteDTO clienteDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            Cliente clienteSave = repository.save(clienteDTO.convert());
            response.put("message", "Conta criada com sucesso!");
            response.put("conta", clienteSave.toString());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", "Erro ao criar conta!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Cliente> getCliente(Integer id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
