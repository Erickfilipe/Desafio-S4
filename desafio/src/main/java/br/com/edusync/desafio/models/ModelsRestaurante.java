package br.com.edusync.desafio.models;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ModelsRestaurante {
    private String nomeRestaurante;
    private String cnpj;
    private String donoRestarante;
    private LocalDate inauguracao;
    private String breveDescricao;

    private List<ModelsFuncionario> funcionariosList;

    public List<ModelsFuncionario> getFuncionariosList() {
        return funcionariosList;
    }
}
