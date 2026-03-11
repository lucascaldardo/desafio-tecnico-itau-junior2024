package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {

    @NotNull
    @PositiveOrZero

    private BigDecimal valor;

    @NotNull
    @PastOrPresent(message = "Data e hora são obrigatórios")
    private OffsetDateTime dataHora;



}
