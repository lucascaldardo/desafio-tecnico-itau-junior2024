package dev.lucascaldardo.desafio.itau.junior2024.Estatistica;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "estatistica")
public record EstatisticasProperties (Integer segundos) {




}
