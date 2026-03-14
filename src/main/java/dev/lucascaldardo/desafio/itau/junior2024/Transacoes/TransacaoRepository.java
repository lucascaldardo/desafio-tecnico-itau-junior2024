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
                resumo.getCount() > 0 ? resumo.getAverage() : 0.0,
                resumo.getCount() > 0 ? resumo.getMax() : 0.0,
                resumo.getCount() > 0 ? resumo.getMin() : 0.0,
                resumo.getSum()
        );
        }

       //Metodo para limpar os dados antigos

    public void limparTransacoesAntigas(Integer segundos) {
        var tempoLimite = OffsetDateTime.now().minusSeconds(segundos);

        // Enquanto a fila não estiver vazia e o item mais antigo  for anterior ao tempo limite remove ele
        while (!pilhatransacoes.isEmpty() && pilhatransacoes.peek().getDataHora().isBefore(tempoLimite)) {
            pilhatransacoes.poll();
        }
    }
}

