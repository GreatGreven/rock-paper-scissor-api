package io.greatgreven.rockpaperscissorapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Roshambo API", version = "1.0", description = "The game of roshambo through an API"
))
public class RockPaperScissorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorApiApplication.class, args);
    }

}
