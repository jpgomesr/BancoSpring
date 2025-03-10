package net.weg.mi75.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.mi75.models.dto.ContaPostRequestDTO;
import net.weg.mi75.models.dto.ContaPutRequestDTO;
import net.weg.mi75.models.dto.ContaResponseDTO;
import net.weg.mi75.models.entity.Conta;
import net.weg.mi75.service.ContaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class ContaController {
    private final ContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO addConta(@RequestBody @Valid ContaPostRequestDTO contaDto) {
        Conta conta = service.addConta(contaDto);
        return conta.convertToContaResponseDTO();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDTO getConta(@PathVariable Integer id) {
        return service.getConta(id).convertToContaResponseDTO();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContaResponseDTO> getAllContas() {
        List<Conta> contaList = service.getAllContas();
        return contaList.stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContaResponseDTO> getAllContasPage(@PageableDefault(size = 20, sort = "saldo",
            direction = Sort.Direction.ASC /* Desc para decrescente */, page = 0) Pageable pageable) {
        Page<Conta> contasPage = service.getAllContas(pageable);
        List<ContaResponseDTO> contasList = contasPage.getContent().stream().map
                (Conta::convertToContaResponseDTO).toList();
        return new PageImpl<>(contasList, contasPage.getPageable(), contasPage.getTotalElements());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, String> deleteConta(@PathVariable Integer id) {
        return service.deleteConta(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDTO updateConta(@PathVariable Integer id, @RequestBody ContaPutRequestDTO contaDto) {
        return service.updateConta(id, contaDto).convertToContaResponseDTO();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDTO changeLimite(@PathVariable Integer id, @RequestParam Double limite) {
        return service.changeLimite(id, limite).convertToContaResponseDTO();
    }
}
