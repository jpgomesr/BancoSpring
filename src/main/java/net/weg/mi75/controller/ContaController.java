package net.weg.mi75.controller;

import jakarta.validation.Valid;
import net.weg.mi75.models.dto.ContaDTO;
import net.weg.mi75.models.entity.Conta;
import net.weg.mi75.service.ContaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addConta(@RequestBody @Valid ContaDTO contaDto) {
        return service.addConta(contaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Integer id) {
        return service.getConta(id);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAllContas() {
        return service.getAllContas();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Conta>> getAllContasPage(@PageableDefault(size = 20, sort = "saldo",
            direction = Sort.Direction.DESC /* Desc para decrescente */, page = 0) Pageable pageable) {
        return service.getAllContas(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteConta(@PathVariable Integer id) {
        return service.deleteConta(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Integer id, @RequestBody Conta conta) {
        return service.updateConta(id, conta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Conta> changeLimite(@PathVariable Integer id, @RequestParam Double limite) {
        return service.changeLimite(id, limite);
    }
}
