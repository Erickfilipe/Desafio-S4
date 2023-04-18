package br.com.edusync.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ModelsFuncionario {

    private ModelsRestaurante restaurante;
    private String nomeFuncionario;
    private Integer cpf;
    private BigDecimal salario;
    private LocalDate admissao;
}
