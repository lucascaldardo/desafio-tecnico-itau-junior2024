package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import dev.lucascaldardo.desafio.itau.junior2024.Estatistica.EstatisticasProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final EstatisticasProperties estatisticasProperties;

    public TransacaoService(TransacaoRepository transacaoRepository, EstatisticasProperties estatisticasProperties) {
        this.transacaoRepository = transacaoRepository;
        this.estatisticasProperties = estatisticasProperties;
    }

    public void adicionarTransacao(TransacaoRequest transacaoRequest) {
        transacaoRepository.salvarDados(transacaoRequest);
    }

    //Limpa a mémoria
    @Scheduled(fixedDelay = 1000)
    public void apagarTransacoesAntigas() {
        transacaoRepository.limparTransacoesAntigas(estatisticasProperties.segundos());
    }

    public void limparTransacoes() {
        transacaoRepository.limparDados();
    }

}