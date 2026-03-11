package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import dev.lucascaldardo.desafio.itau.junior2024.Estatistica.EstatisticasDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class TransacaoRepository{

    private final Queue<TransacaoRequest> pilhatransacoes = new ConcurrentLinkedQueue<>();

    public void salvarDados(TransacaoRequest transacaoRequest) {
        pilhatransacoes.add(transacaoRequest);
    }

    public void limparDados() {
        pilhatransacoes.clear();
    }

    public EstatisticasDTO estatistica(OffsetDateTime horaInicial) {

        if (pilhatransacoes.isEmpty()) {
            return new EstatisticasDTO(0, 0, 0, 0, 0);
        }

        final var resumo = pilhatransacoes.stream()
                .filter(t ->
                        t.getDataHora().isAfter(horaInicial) || t.getDataHora().isEqual(horaInicial)
                ).mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
        return new EstatisticasDTO(
                resumo.getCount(),
                resumo.getAverage(),
                resumo.getMax(),
                resumo.getMin(),
                resumo.getSum());
        }




    }

