package br.com.edusync.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ModelsRestaurante {
    private String cnpj;
    private String donoRestarante;
    private LocalDate inauguracao;
    private String breveDescricao;
    private List<ModelsFuncionario> modelsFuncionarios;
}
