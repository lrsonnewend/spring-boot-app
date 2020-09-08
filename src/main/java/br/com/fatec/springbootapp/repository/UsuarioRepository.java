package br.com.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fatec.springbootapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findByNomeContainsIgnoreCase(String nome);
    
    public Usuario findByNome(String nome);

    public Usuario findByNomeAndSenha(String nome, String senha);

    public List<Usuario> findByAutorizacoesNome(String nome);

    @Query("SELECT u FROM Usuario u WHERE u.nome = ?1")
    public Usuario buscaUsuarioPorNome(String nome);

    @Query("SELECT u FROM Usuario u WHERE u.nome = ?1 and u.senha = ?2")
    public Usuario buscaUsuarioPorNomeESenha(String nome, String senha);

    @Query("SELECT u FROM Usuario u INNER JOIN u.autorizacoes a WHERE a.nome = ?1")
    public List<Usuario> buscaPorNomeAutorizacao(String autorizacao);
}