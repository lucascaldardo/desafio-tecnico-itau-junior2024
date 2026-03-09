package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacoesRequest {

    @NotNull
    @Min(0)

    private BigDecimal valor;

    @NotNull
    @PastOrPresent
    private OffsetDateTime dataHora;



}
