package com.github.isaconstance.registro_isabella_559219_m1.service;

import com.github.isaconstance.registro_isabella_559219_m1.dto.RegistroRequestDTO;
import com.github.isaconstance.registro_isabella_559219_m1.dto.RegistroResponseDTO;
import com.github.isaconstance.registro_isabella_559219_m1.entities.Registro;
import com.github.isaconstance.registro_isabella_559219_m1.exceptions.ResourceNotFoundException;
import com.github.isaconstance.registro_isabella_559219_m1.repositories.RegistroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Transactional(readOnly = true)
    public List<RegistroResponseDTO> findAllRegistros(){
        return registroRepository.findAll()
                .stream().map(RegistroResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RegistroResponseDTO findRegistroById(Long id){
        Registro registro = registroRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new RegistroResponseDTO(registro);
    }

    @Transactional
    public RegistroResponseDTO saveRegistro(RegistroRequestDTO inputDTO) {
        Registro registro = new Registro();
        copyDtoToRegistro(inputDTO, registro);
        registro = registroRepository.save(registro);
        return new RegistroResponseDTO(registro);
    }

    @Transactional
    public RegistroResponseDTO updateRegistro(Long id, RegistroRequestDTO inputDTO) {
        try {
            Registro registro = registroRepository.getReferenceById(id);
            copyDtoToRegistro(inputDTO, registro);
            registro = registroRepository.save(registro);
            return new RegistroResponseDTO(registro);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteRegistroById(Long id) {
        if (!registroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        registroRepository.deleteById(id);
    }

    private void copyDtoToRegistro(RegistroRequestDTO inputDTO, Registro registro) {
        registro.setEstacaoNome(inputDTO.getEstacaoNome());
        registro.setDataRegistro(inputDTO.getDataRegistro());
        registro.setTemperatura(inputDTO.getTemperatura());
        registro.setPercentualUmi(inputDTO.getPercentualUmi());
        registro.setPrecipitacao(inputDTO.getPrecipitacao());
        registro.setCondicaoClimatica(inputDTO.getCondicaoClimatica());
    }
}
