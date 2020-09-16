package br.com.fatec.springbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fatec.springbootapp.entity.Autorizacao;
import br.com.fatec.springbootapp.entity.Usuario;
import br.com.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private SegurancaService segService;

    @GetMapping
    public List<Usuario> buscarTodosUsuarios(){
        return segService.buscarTodosUsuarios();
    }

    @JsonView(View.UsuarioCompleto.class)
    @GetMapping(value="/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id){
        return segService.buscarUsuarioPorId(id);
    }

    @JsonView(View.UsuarioResumo.class)
    @GetMapping(value="/nome")
    public Usuario buscarPorNome(@RequestParam(value="nome") String nome){
        return segService.buscarUsuarioPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder){
        usuario = segService.criarUsuario(usuario.getNome(), usuario.getSenha(), "ROLE_USER");

        HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
                uriComponentsBuilder.path(
                    "/usuario/" + usuario.getId()).build().toUri());
            
        return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.AutorizacaoResumo.class)
    @GetMapping(value = "/autorizacao/{autorizacao}")
    public Autorizacao buscarAutorizacaoPorNome(@PathVariable("autorizacao") String nome){
        return segService.buscarAutPorNome(nome);
    }
}