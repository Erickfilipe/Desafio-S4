package br.com.edusync.desafio.controllerRestaurante;

import br.com.edusync.desafio.models.ModelsRestaurante;
import br.com.edusync.desafio.service.ServiceRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping(value = "/restaurantes")
@RestController
public class ControllerRestaurante {

    @Autowired
    private ServiceRestaurante serviceRestaurante;

    @PostMapping(value = "/novo")
    public ResponseEntity abrirNovoRestaurante (@RequestBody ModelsRestaurante restaurante) {
        try {
            serviceRestaurante.abrirNovoRestaurante(restaurante);
            return new ResponseEntity<>(restaurante, HttpStatus.CREATED);
        }catch (HttpMessageNotReadableException e){
            return new ResponseEntity<>("Faltam informações ao cadastro" ,HttpStatus.OK);
        }
    }
    @GetMapping
    public ResponseEntity listarTodosOsRestaurantes(){
        try {
            return new ResponseEntity(serviceRestaurante.listarTodosOsRestaurantes(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: A lista está vazia", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{cnpj}")
    public ResponseEntity BuscarPorCnpj(@PathVariable String cnpj){
        try {
            return new ResponseEntity(serviceRestaurante.buscarPorCnpj(cnpj), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: Este CNPJ não existe", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{cnpj}")
    public ResponseEntity alterarDadosDoRestaurantes(@PathVariable String cnpj, @RequestBody ModelsRestaurante Restaurante){
        try {
            serviceRestaurante.alterarDados(cnpj, Restaurante);
            return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: Esse CNPJ não foi encontrado" ,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{cnpj}")
    public ResponseEntity fecharRestaurante(@PathVariable String cnpj){
        try {
            serviceRestaurante.fechar(cnpj);
            return new ResponseEntity("Erro: O restaurante foi fechado", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Erro: Restaurante não encontrado", HttpStatus.OK);
        }
    }
}
