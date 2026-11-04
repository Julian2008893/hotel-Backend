package co.edu.cesde.ga.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Backend API")
                        .version("1.0.0")
                        .description("API REST para la administración de hospedajes de un hotel (Arquitectura Hexagonal). " +
                                "Permite registrar clientes, consultar disponibilidad, realizar Check-In y Check-Out, " +
                                "y generar facturas.")
                        .contact(new Contact()
                                .name("Taller Backend Hotel")
                                .email("soporte@hotel.com")));
    }
}
