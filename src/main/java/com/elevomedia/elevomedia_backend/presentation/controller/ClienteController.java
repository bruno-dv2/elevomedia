package com.elevomedia.elevomedia_backend.presentation.controller;

import com.elevomedia.elevomedia_backend.application.service.ClienteService;
import com.elevomedia.elevomedia_backend.presentation.dto.request.ClienteRequestDTO;
import com.elevomedia.elevomedia_backend.presentation.dto.response.ClienteResponseDTO;
import com.elevomedia.elevomedia_backend.presentation.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @RequestParam Long usuarioId,
            @RequestBody @Valid ClienteRequestDTO dto) {

        var cliente = clienteMapper.toEntity(dto);
        var salvo = clienteService.criar(usuarioId, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toResponse(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(clienteMapper.toResponse(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarPorUsuario(@RequestParam Long usuarioId) {
        var clientes = clienteService.listarPorUsuario(usuarioId);
        var response = clientes.stream()
                .map(clienteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteRequestDTO dto) {

        var dadosNovos = clienteMapper.toEntity(dto);
        var atualizado = clienteService.atualizar(id, dadosNovos);
        return ResponseEntity.ok(clienteMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        clienteService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
