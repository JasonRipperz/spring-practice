package co.edu.unicundi.proyectoSpringPrueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProyectoSpringPruebaApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(ProyectoSpringPruebaApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringPruebaApplication.class, args);
	}

}
