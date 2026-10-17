package com.github.isaconstance.registro_isabella_559219_m1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String estacaoNome;
    @Column(nullable = false)
    private LocalDate dataRegistro;
    @Column(nullable = false)
    private BigDecimal temperatura;
    @Column(nullable = false)
    private BigDecimal percentualUmi;
    @Column(nullable = false)
    private BigDecimal precipitacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CondicaoClimatica condicaoClimatica;
}
