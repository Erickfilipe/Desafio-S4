package br.com.edusync.desafio.service;

import br.com.edusync.desafio.models.ModelsFuncionario;
import br.com.edusync.desafio.models.ModelsRestaurante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceRestaurante {
    private List<ModelsRestaurante> restaurantes = new ArrayList<>();

    public void abrirNovoRestaurante(ModelsRestaurante restaurante){
        restaurantes.add(restaurante);
    }
    public List<ModelsRestaurante> listarTodosOsRestaurantes() {
        return restaurantes;
    }

    public ModelsRestaurante buscarPorCnpj(String cnpj){
        return restaurantes.stream().filter(p -> cnpj.equals(p.getCnpj())).findFirst().orElseThrow();
    }

    public void alterarDados(String cnpj, ModelsRestaurante restaurante) {
        ModelsRestaurante restauranteantigo = buscarPorCnpj(cnpj);
        if (restauranteantigo != null){
            restaurantes.remove(restauranteantigo);
        }
        restaurantes.add(restaurante);
    }

    public void fechar(String cnpj) {
        ModelsRestaurante restaurante = buscarPorCnpj(cnpj);
        if (restaurante != null){
            restaurantes.remove(restaurante);
        }
        restaurantes.remove(restaurante);
    }


}
