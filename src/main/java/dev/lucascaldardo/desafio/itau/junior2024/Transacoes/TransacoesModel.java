package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacoesModel {

    private BigDecimal valor;
    private OffsetDateTime dataHora;

}
