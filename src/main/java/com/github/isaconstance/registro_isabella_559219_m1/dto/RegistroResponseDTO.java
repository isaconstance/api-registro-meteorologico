package com.github.isaconstance.registro_isabella_559219_m1.dto;

import com.github.isaconstance.registro_isabella_559219_m1.entities.CondicaoClimatica;
import com.github.isaconstance.registro_isabella_559219_m1.entities.Registro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistroResponseDTO {

    private Long id;
    private String estacaoNome;
    private LocalDate dataRegistro;
    private BigDecimal temperatura;
    private BigDecimal percentualUmi;
    private BigDecimal precipitacao;
    private CondicaoClimatica condicaoClimatica;

    public RegistroResponseDTO(Registro registro) {
        id = registro.getId();
        estacaoNome = registro.getEstacaoNome();
        dataRegistro = registro.getDataRegistro();
        temperatura = registro.getTemperatura();
        percentualUmi = registro.getPercentualUmi();
        precipitacao = registro.getPrecipitacao();
        condicaoClimatica = registro.getCondicaoClimatica();
    }
}
