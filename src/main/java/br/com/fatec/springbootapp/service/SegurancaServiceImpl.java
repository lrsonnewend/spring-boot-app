package br.com.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.springbootapp.entity.Autorizacao;
import br.com.fatec.springbootapp.entity.Usuario;
import br.com.fatec.springbootapp.exception.RegistroNaoEncontrado;
import br.com.fatec.springbootapp.repository.AutorizacaoRepository;
import br.com.fatec.springbootapp.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private UsuarioRepository userRepo;
    
    @Override
    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao auto = autRepo.findByNome(autorizacao);
        if(auto == null){
            auto = new Autorizacao();
            auto.setNome(autorizacao);
            autRepo.save(auto);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(auto);
        userRepo.save(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodosUsuarios(){
        return userRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id){
        Optional<Usuario> usuarioOp = userRepo.findById(id);
        
        if(usuarioOp.isPresent())
            return usuarioOp.get();
        
        throw new RegistroNaoEncontrado("Usuário não encontrado.");
   }

   @Override
   public Usuario buscarUsuarioPorNome(String nome){
        Usuario usuario = userRepo.findByNome(nome);

        if(usuario != null)
            return usuario;
        
        throw new RegistroNaoEncontrado("Usuário não encontrado.");
   }

   @Override
   public Autorizacao buscarAutPorNome(String nome) {
       Autorizacao autorizacao = autRepo.findByNome(nome);
       
       if(autorizacao != null)
            return autorizacao;
        
        throw new RegistroNaoEncontrado("Autorização não encontrada.");
   }
}