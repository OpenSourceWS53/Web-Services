package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
<<<<<<< HEAD
    public OpenAPI learningPlatformOpenApi() {
=======
    public OpenAPI chaquitacllaPlatformOpenApi() {
>>>>>>> bc-products
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Chaquitaclla Platform API")
<<<<<<< HEAD
                        .description("User Platform application REST API documentation.")
=======
                        .description("Chaquitaclla Platform application REST API documentation.")
>>>>>>> bc-products
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
<<<<<<< HEAD
                        .description("User Platform Documentation")
                        .url("https://github.com/SI729-WS53-AgriCulture/Chaquitaclla-web-services/tree/bc-users"));
        return openApi;
    }
}
=======
                        .description("Chaquitaclla Platform Documentation")
                        .url("https://github.com/SI729-WS53-AgriCulture/Chaquitaclla-web-services"));
        return openApi;
    }
}
>>>>>>> bc-products
