package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.dto.usuario.UsuarioLista;
import br.com.fiap.api_agrovista.dto.usuario.UsuarioRequest;
import br.com.fiap.api_agrovista.dto.usuario.UsuarioResponse;
import br.com.fiap.api_agrovista.mapper.UsuarioMapper;
import br.com.fiap.api_agrovista.model.Plano;
import br.com.fiap.api_agrovista.model.Usuario;
import br.com.fiap.api_agrovista.model.enums.RoleUsuario;
import br.com.fiap.api_agrovista.repository.PlanoRepository;
import br.com.fiap.api_agrovista.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PlanoRepository planoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PlanoRepository planoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.planoRepository = planoRepository;
    }

    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Plano plano = planoRepository.findById(usuarioRequest.idPlano())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setTelefone(usuarioRequest.telefone());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioRequest.senha()));
        usuario.setNomeFazenda(usuarioRequest.nomeFazenda());
        usuario.setMunicipio(usuarioRequest.municipio());
        usuario.setEstado(usuarioRequest.estado());
        usuario.setAreaHectares(usuarioRequest.areaHectares());
        usuario.setCultura(usuarioRequest.cultura());
        usuario.setRole(RoleUsuario.USUARIO);
        usuario.setPlano(plano);

        return usuarioMapper.usuarioToResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse findById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return usuarioMapper.usuarioToResponse(usuario);
    }

    public Page<UsuarioLista> findAll(Pageable pageable) {
        return usuarioRepository
                .findAll(pageable)
                .map(usuarioMapper::usuarioToResponseLista);
    }

    public UsuarioResponse update(UUID id, UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(usuarioRequest.idPlano())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        usuario.setNome(usuarioRequest.nome());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setTelefone(usuarioRequest.telefone());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioRequest.senha()));
        usuario.setNomeFazenda(usuarioRequest.nomeFazenda());
        usuario.setMunicipio(usuarioRequest.municipio());
        usuario.setEstado(usuarioRequest.estado());
        usuario.setAreaHectares(usuarioRequest.areaHectares());
        usuario.setCultura(usuarioRequest.cultura());
        usuario.setPlano(plano);

        return usuarioMapper.usuarioToResponse(usuarioRepository.save(usuario));
    }

    public void delete(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}