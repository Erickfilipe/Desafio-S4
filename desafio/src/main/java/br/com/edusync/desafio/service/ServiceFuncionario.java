package br.com.edusync.desafio.service;

import br.com.edusync.desafio.models.ModelsFuncionario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceFuncionario {

    private List<ModelsFuncionario> funcionarios = new ArrayList<>();

    public void contratarFuncionario(ModelsFuncionario funcionario){
        funcionarios.add(funcionario);
    }
    public String listarTodosOsFuncionarios() {
        verificar(funcionarios);
        return verificar(funcionarios);
    }

    public String verificar(List<ModelsFuncionario> funcionarios){
        if (this.funcionarios == null){
            return "A lista está vazia";
        }
        return " ";
    }
    public ModelsFuncionario buscarPorCpf(Integer cpf){
        return funcionarios.stream().filter(p -> cpf.equals(p.getCpf())).findFirst().orElseThrow();
    }

    public void alterarDadosDoFuncionario(Integer cpf, ModelsFuncionario funcionario) {
        ModelsFuncionario funcionarioantigo = buscarPorCpf(cpf);
        if (funcionarioantigo != null){
            funcionarios.remove(funcionarioantigo);
        }
        funcionarios.add(funcionario);
    }

    public void demissao(Integer cpf) {
        ModelsFuncionario funcionario = buscarPorCpf(cpf);
        if (funcionario != null){
            funcionarios.remove(funcionario);
        }
        funcionarios.remove(funcionario);
    }

}
