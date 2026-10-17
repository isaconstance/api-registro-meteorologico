package com.github.isaconstance.registro_isabella_559219_m1.dto;

import com.github.isaconstance.registro_isabella_559219_m1.entities.CondicaoClimatica;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistroRequestDTO {

    @NotBlank(message = "Nome da estação requerido")
    @Size(min = 3, max = 100, message = "O nome da estação deve ter entre 3 e 100 caracteres")
    private String estacaoNome;

    @NotNull(message = "Data do registro requerida")
    @PastOrPresent(message = "A data do registro não pode ser futura")
    private LocalDate dataRegistro;

    @NotNull(message = "Temperatura requerida")
    private BigDecimal temperatura;

    @NotNull(message = "Percentual de umidade requerido")
    @DecimalMin(value = "0", message = "O percentual de umidade deve ser no mínimo 0")
    @DecimalMax(value = "100", message = "O percentual de umidade deve ser no máximo 100")
    private BigDecimal percentualUmi;

    @NotNull(message = "Precipitação requerida")
    @PositiveOrZero(message = "A precipitação deve ser maior ou igual a zero")
    private BigDecimal precipitacao;

    @NotNull(message = "Condição climática requerida")
    private CondicaoClimatica condicaoClimatica;
}
