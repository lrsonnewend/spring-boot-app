package br.com.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    @Transactional

    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao auto = autRepo.findByNome(autorizacao);
        if (auto == null) {
            auto = new Autorizacao();
            auto.setNome(autorizacao);
            autRepo.save(auto);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(auto);
        userRepo.save(usuario);
        return usuario;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {
        return userRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOp = userRepo.findById(id);

        if (usuarioOp.isPresent())
            return usuarioOp.get();

        throw new RegistroNaoEncontrado("Usuário não encontrado.");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = userRepo.findByNome(nome);

        if (usuario != null)
            return usuario;

        throw new RegistroNaoEncontrado("Usuário não encontrado.");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutPorNome(String nome) {
        Autorizacao autorizacao = autRepo.findByNome(nome);

        if (autorizacao != null)
            return autorizacao;

        throw new RegistroNaoEncontrado("Autorização não encontrada.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByNome(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário "+username+" não encontrado.");
        }
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes()
            .stream()
            .map(Autorizacao::getNome)
            .collect(Collectors.toList())
            .toArray(new String[usuario.getAutorizacoes().size()]))
            .build();
    }
}