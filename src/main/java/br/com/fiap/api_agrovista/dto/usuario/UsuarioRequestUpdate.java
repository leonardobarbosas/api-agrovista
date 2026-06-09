package br.com.fiap.api_agrovista.dto.usuario;

import br.com.fiap.api_agrovista.model.enums.RoleUsuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioRequestUpdate(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotBlank(message = "Nome da fazenda é obrigatório")
        String nomeFazenda,

        @NotBlank(message = "Município é obrigatório")
        String municipio,

        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ser a sigla com 2 letras")
        String estado,

        @NotBlank(message = "A Role é obrigatória")
        RoleUsuario role,

        @NotNull(message = "Área em hectares é obrigatória")
        @Positive(message = "Área deve ser maior que zero")
        Double areaHectares,

        @NotBlank(message = "Cultura é obrigatória")
        String cultura,

        @NotNull(message = "Plano é obrigatório")
        UUID idPlano
) {
}
