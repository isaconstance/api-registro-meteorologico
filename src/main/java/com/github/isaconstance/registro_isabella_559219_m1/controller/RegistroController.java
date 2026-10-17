package com.github.isaconstance.registro_isabella_559219_m1.controller;

import com.github.isaconstance.registro_isabella_559219_m1.dto.RegistroRequestDTO;
import com.github.isaconstance.registro_isabella_559219_m1.dto.RegistroResponseDTO;
import com.github.isaconstance.registro_isabella_559219_m1.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> getAllRegistros(){
        List<RegistroResponseDTO> list = registroService.findAllRegistros();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> getRegistro(@PathVariable Long id) {
        RegistroResponseDTO registroDTO = registroService.findRegistroById(id);
        return ResponseEntity.ok(registroDTO);
    }

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> createRegistro(
            @RequestBody @Valid RegistroRequestDTO inputDTO) {

        RegistroResponseDTO registroDTO = registroService.saveRegistro(inputDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(registroDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(registroDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> updateRegistro(@PathVariable Long id,
                                                              @RequestBody @Valid RegistroRequestDTO inputDTO) {

        RegistroResponseDTO registroDTO = registroService.updateRegistro(id, inputDTO);
        return ResponseEntity.ok(registroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        registroService.deleteRegistroById(id);
        return ResponseEntity.noContent().build();
    }


}
