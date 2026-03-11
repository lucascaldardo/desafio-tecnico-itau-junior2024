package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void validarTransacao(TransacaoRequest transacaoRequest) {

        if (transacaoRequest.getValor().compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException ("Erro: Transação inválida, transações devem ter valor maior ou igual a zero.");
        }

        if (transacaoRequest.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException ("Erro: Transação inválida, datas devem ser em uma data passada ou presente.");
        }


    }

}
