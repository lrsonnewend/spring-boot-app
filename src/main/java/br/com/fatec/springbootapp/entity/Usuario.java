package br.com.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.fatec.springbootapp.controller.View;

@Entity
@Table(name="usuario")
public class Usuario {
    
    @JsonView(View.UsuarioCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    

    @JsonView({View.UsuarioResumo.class, View.AutorizacaoResumo.class})
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "senha")
    private String senha;

    @JsonView(View.UsuarioResumo.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_autorizacao",
    joinColumns = { @JoinColumn(name = "id_usuario") },
    inverseJoinColumns = { @JoinColumn(name = "id_aut") })

    private Set<Autorizacao> autorizacoes;
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }
}