package br.com.fatec.springbootapp.service;

import br.com.fatec.springbootapp.entity.Usuario;

public interface SegurancaService {
    public Usuario criarUsuario(String nome, String senha, String autorizacao);
}