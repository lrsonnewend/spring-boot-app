package br.com.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.springbootapp.entity.Autorizacao;
import br.com.fatec.springbootapp.entity.Usuario;
import br.com.fatec.springbootapp.repository.AutorizacaoRepository;
import br.com.fatec.springbootapp.repository.UsuarioRepository;
import br.com.fatec.springbootapp.service.SegurancaService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository urep;

    @Autowired
    private AutorizacaoRepository arep;

    @Autowired
    private SegurancaService segService;
    
	@Test
	void contextLoads() {
    }
    
    /* @Test
    void testInsert(){
        Usuario user = new Usuario();
        user.setNome("cacobarcelo");
        user.setSenha("c0r1nter3Def");
        urep.save(user);
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO2");
        aut.setUsuarios(new HashSet<Usuario>());
        aut.getUsuarios().add(user);
        arep.save(aut);
        assertNotNull(aut.getUsuarios().iterator().next().getId());
    }  */

    /* @Test
    void testAutorizacao(){
        Usuario user = urep.findById(11L).get();
        assertEquals("ROLE_ADMIN", user.getAutorizacoes().iterator().next().getNome());
    } 

    @Test
    void testUser(){
        Autorizacao aut = arep.findById(1L).get();
        assertEquals("ronaldo", aut.getUsuarios().iterator().next().getNome());
    } 

    @Test
    void testSearchUserByNameContains(){
        List<Usuario> usuarios = urep.findByNomeContainsIgnoreCase("R");
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void testSearchUserByName(){
        Usuario usuario = urep.findByNome("cacobarcelo");
        assertNotNull(usuario);
    }

    @Test
    void testSearchUserByLog(){
        Usuario usuario = urep.findByNomeAndSenha("ronaldo", "1234");
        assertNotNull(usuario);
    }

    @Test
    void testSearchUserByNameAuth(){
        List<Usuario> usuarios = urep.findByAutorizacoesNome("ROLE_USUARIO");
        assertFalse(usuarios.isEmpty());
    } */

/*      @Test
    void testaBuscaUsuarioNomeQuery(){
        Usuario usuario = urep.buscaUsuarioPorNome("cacobarcelo");
        assertNotNull(usuario);
    }

    @Test
    void testaBuscaUsuarioNomeESenhaQuery(){
        Usuario usuario = urep.buscaUsuarioPorNomeESenha("cacobarcelo", "c0r1nter3Def");
        assertNotNull(usuario);
    }

    @Test
    void testaBuscaUsuarioNomeAutorizacaoQuery(){
        List<Usuario> usuarios = urep.buscaPorNomeAutorizacao("ROLE_USUARIO2");
        assertFalse(usuarios.isEmpty());
    } */

    @Test
    void testaServicoCriaUsuario(){
        Usuario usuario = segService.criarUsuario("ademir", "ademir123", "ROLE_ADEMIRO");
        assertNotNull(usuario);
    }
}