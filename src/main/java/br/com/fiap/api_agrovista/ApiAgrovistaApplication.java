package br.com.fiap.api_agrovista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiAgrovistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAgrovistaApplication.class, args);
	}

}
