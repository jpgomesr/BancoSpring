package net.weg.mi75.controller;

import net.weg.mi75.models.dto.ClienteDTO;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    ClienteService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> addCliente(@RequestBody ClienteDTO clienteDTO) {
        return service.addCliente(clienteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
        return service.getCliente(id);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return service.getAllClientes();
    }
}
