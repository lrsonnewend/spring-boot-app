package br.com.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.fatec.springbootapp.controller.View;

@Entity
@Table(name="autorizacao")
public class Autorizacao {

    @JsonView(View.UsuarioCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aut")
    private Long id;
    
    @JsonView({View.UsuarioResumo.class, View.AutorizacaoResumo.class})
    @Column(name = "aut_nome")
    private String nome;
    
    @JsonView(View.AutorizacaoResumo.class)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "autorizacoes")
    private Set<Usuario> usuarios;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}