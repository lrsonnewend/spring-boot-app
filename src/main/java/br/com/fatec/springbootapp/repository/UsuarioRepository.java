package br.com.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatec.springbootapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}