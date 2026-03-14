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

    @NotNull(message = "O valor é obrigatório")
    @PositiveOrZero(message = "O valor deve ser positivo e maior que 0")

    private BigDecimal valor;

    @NotNull(message = "Data e hora são obrigatórios")
    @PastOrPresent(message = "A data deve ser no passo ou no presente, não podendo ser em uma data futura")
    private OffsetDateTime dataHora;



}
