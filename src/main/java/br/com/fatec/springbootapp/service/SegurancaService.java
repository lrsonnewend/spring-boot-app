package br.com.fatec.springbootapp.service;

import java.util.List;

import br.com.fatec.springbootapp.entity.Usuario;

public interface SegurancaService {
    
    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);
}