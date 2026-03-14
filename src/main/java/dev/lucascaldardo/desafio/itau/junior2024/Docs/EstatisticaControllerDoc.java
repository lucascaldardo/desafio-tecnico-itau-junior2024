package dev.lucascaldardo.desafio.itau.junior2024.Docs;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

public interface EstatisticaControllerDoc {

    @Operation(summary = "Pega as transações feitas somente dentro de um período de 60 segundos",
            description = "Quaisquer transações feitas antes do período de 60 segundos não irão ser consideradas")
    ResponseEntity estatistica();
}
