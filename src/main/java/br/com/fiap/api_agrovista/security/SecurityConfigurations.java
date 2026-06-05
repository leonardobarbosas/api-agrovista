package br.com.fiap.api_agrovista.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                                .requestMatchers(HttpMethod.POST, "/usuario/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuario/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/usuario/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/usuario/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/talhao/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/talhao/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/talhao/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/talhao/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/alerta/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/alerta/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/alerta/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/alerta/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/conversa/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/conversa/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/conversa/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/conversa/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/historico/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/historico/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/historico/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/historico/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/laudo/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/laudo/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/laudo/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/laudo/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/mensagem/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/mensagem/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/mensagem/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/mensagem/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/plano/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/plano/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/plano/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/plano/**").hasRole("ADMIN")

                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs.yaml"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
