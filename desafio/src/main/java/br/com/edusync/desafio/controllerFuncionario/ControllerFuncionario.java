package br.com.edusync.desafio.controllerFuncionario;
import br.com.edusync.desafio.models.ModelsFuncionario;
import br.com.edusync.desafio.models.ModelsRestaurante;
import br.com.edusync.desafio.service.ServiceFuncionario;
import br.com.edusync.desafio.service.ServiceRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping(value = "/funcionario")
@RestController

public class ControllerFuncionario {
    @Autowired
    private ServiceFuncionario serviceFuncionario;
    @Autowired
    private ServiceRestaurante serviceRestaurante;

    // Get - Trazer, Post - Cadastrar, Put - Editar, Patch - Editar, Delete - Deletar
    @PostMapping(value = "/novo")
    public ResponseEntity contratarFuncionario (@RequestBody ModelsFuncionario funcionario,
        @RequestParam String cnpj){
        try{
            ModelsRestaurante restaurante = serviceRestaurante.buscarPorCnpj(cnpj);
            serviceFuncionario.contratarFuncionario(funcionario);
            funcionario.setRestaurante(serviceRestaurante.buscarPorCnpj(cnpj));
            restaurante.getModelsFuncionarios().add(funcionario);
            return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
        }catch (HttpMessageNotReadableException e){
            return new ResponseEntity<>("Erro: Faltam informações ao cadastro" ,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: Faltam informações ao cadastro (Restaurante)" ,HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity listarTodosOsFuncionario(){
       try{
           return new ResponseEntity(serviceFuncionario.listarTodosOsFuncionarios(), HttpStatus.OK);
       }catch (NoSuchElementException e){
           return new ResponseEntity<>("Erro: A lista está vazia", HttpStatus.OK);
       }
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable Integer cpf){
        try {
            return new ResponseEntity(serviceFuncionario.buscarPorCpf(cpf), HttpStatus.OK);
        }catch (NoSuchElementException e){
          return new ResponseEntity<>("Erro: Este CPF não existe", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{cpf}")
    public ResponseEntity alterarDadosFuncionario(@PathVariable Integer cpf, @RequestBody ModelsFuncionario funcionario){
        try {
            serviceFuncionario.alterarDadosDoFuncionario(cpf, funcionario);
            return new ResponseEntity(funcionario, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Erro: Este CPF não existe", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity demitirFuncionario(@PathVariable Integer cpf){
        try {
            serviceFuncionario.demissao(cpf);
            return new ResponseEntity("Erro: Funcionário demitido(a)", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: Funcionário não encontrado(a)" ,HttpStatus.OK);
        }
    }
}
