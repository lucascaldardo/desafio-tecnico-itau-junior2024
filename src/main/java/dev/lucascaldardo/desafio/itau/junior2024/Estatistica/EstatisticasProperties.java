package dev.lucascaldardo.desafio.itau.junior2024.Estatistica;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "estatistica")
public record EstatisticasProperties (@NotNull @Positive Integer segundos) {




}
