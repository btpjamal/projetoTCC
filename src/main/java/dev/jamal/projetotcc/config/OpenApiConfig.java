package dev.jamal.projetotcc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hobbyRecomendationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Recomendação de Hobbies")
                        .version("1.0.0")
                        .description("API REST para recomendação personalizada de hobbies com base em perfil, interesses e feedback do usuário"));
    }
}
