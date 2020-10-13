package br.com.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.fatec.springbootapp.entity.Autorizacao;
import br.com.fatec.springbootapp.entity.Usuario;

public interface SegurancaService extends UserDetailsService{
    
    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutPorNome(String nome);
}