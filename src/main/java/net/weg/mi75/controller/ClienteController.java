package net.weg.mi75.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.mi75.models.dto.ClientePostRequestDTO;
import net.weg.mi75.models.dto.ClientePutRequestDTO;
import net.weg.mi75.models.dto.ClienteResponseDTO;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO addCliente(@RequestBody @Valid ClientePostRequestDTO clienteDto) {
        Cliente cliente = service.addCliente(clienteDto);
        return cliente.convertToClienteResponseDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO editCliente(@PathVariable Integer id,
                                          @RequestBody @Valid ClientePutRequestDTO clienteDto) {
        Cliente cliente = service.editCliente(id, clienteDto);
        return cliente.convertToClienteResponseDTO();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO changeContas(@PathVariable Integer id, @RequestParam Integer idConta) {
        Cliente cliente = service.changeContas(id, idConta);
        return cliente.convertToClienteResponseDTO();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO getClienteById(@PathVariable Integer id) {
        Cliente cliente = service.getClienteById(id);
        return cliente.convertToClienteResponseDTO();
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteResponseDTO> getClientes(
            @PageableDefault(
                    size = 20,
                    sort = "nome",
                    direction = Sort.Direction.ASC,
                    page = 0
            ) Pageable pageable
    ) {
        Page<Cliente> clientePage = service.getClientes(pageable);
        List<ClienteResponseDTO> contentList = clientePage.getContent().
                stream().map(Cliente::convertToClienteResponseDTO).toList();
        return new PageImpl<>(contentList, clientePage.getPageable(),
                clientePage.getTotalElements());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {
        service.deleteCliente(id);
    }
}
