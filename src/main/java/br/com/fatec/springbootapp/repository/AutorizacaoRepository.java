package br.com.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatec.springbootapp.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{
    
}