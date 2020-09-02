package br.com.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fatec.springbootapp.entity.Usuario;
import br.com.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository urep;
    
	@Test
	void contextLoads() {
    }
    
    @Test
    void testInsert(){
        Usuario user = new Usuario();
        
        user.setNome("babaca");
        
        user.setSenha("42er3@Def");
        
        urep.save(user);

        assertNotNull(user.getId());
    }

}
