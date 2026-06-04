package br.com.fiap.api_agrovista.controller;

import br.com.fiap.api_agrovista.dto.usuario.AuthDTO;
import br.com.fiap.api_agrovista.dto.usuario.LoginResponseDTO;
import br.com.fiap.api_agrovista.dto.usuario.UsuarioRequest;
import br.com.fiap.api_agrovista.model.Plano;
import br.com.fiap.api_agrovista.model.Usuario;
import br.com.fiap.api_agrovista.model.enums.RoleUsuario;
import br.com.fiap.api_agrovista.repository.PlanoRepository;
import br.com.fiap.api_agrovista.repository.UsuarioRepository;
import br.com.fiap.api_agrovista.service.TokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          UsuarioRepository usuarioRepository,
                          PlanoRepository planoRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        System.out.println(">>> LOGIN CHAMADO: " + authDTO.email());
        var usuarioSenha = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
        var auth = authenticationManager.authenticate(usuarioSenha);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        Plano plano = planoRepository.findById(request.idPlano())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.nome());
        novoUsuario.setCpf(request.cpf());
        novoUsuario.setTelefone(request.telefone());
        novoUsuario.setEmail(request.email());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(request.senha()));
        novoUsuario.setNomeFazenda(request.nomeFazenda());
        novoUsuario.setMunicipio(request.municipio());
        novoUsuario.setEstado(request.estado());
        novoUsuario.setAreaHectares(request.areaHectares());
        novoUsuario.setCultura(request.cultura());
        novoUsuario.setRole(RoleUsuario.USUARIO);
        novoUsuario.setPlano(plano);

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}