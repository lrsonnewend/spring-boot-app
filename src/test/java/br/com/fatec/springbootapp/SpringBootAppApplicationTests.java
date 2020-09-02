package br.com.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.springbootapp.entity.Autorizacao;
import br.com.fatec.springbootapp.entity.Usuario;
import br.com.fatec.springbootapp.repository.AutorizacaoRepository;
import br.com.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository urep;

    @Autowired
    private AutorizacaoRepository arep;
    
	@Test
	void contextLoads() {
    }
    
    @Test
    void testInsert(){
        Usuario user = new Usuario();
        user.setNome("agnaldo");
        user.setSenha("c0r1nter3@Def");
        user.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();
        aut.setId(1L);
        aut.setNome("ROLE_USUARIO");
        arep.save(aut);
        user.getAutorizacoes().add(aut);
        urep.save(user);
        assertNotNull(user.getAutorizacoes().iterator().next().getId());
    }

    @Test
    void testAutorizacao(){
        Usuario user = urep.findById(3L).get();
        assertEquals("ROLE_ADMIN", user.getAutorizacoes().iterator().next().getNome());
    }

    @Test
    void testUser(){
        Autorizacao aut = arep.findById(1L).get();
        assertEquals("ronaldo", aut.getUsuarios().iterator().next().getNome());
    }
}