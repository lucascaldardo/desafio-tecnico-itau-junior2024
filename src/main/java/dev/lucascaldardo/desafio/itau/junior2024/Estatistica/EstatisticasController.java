package dev.lucascaldardo.desafio.itau.junior2024.Estatistica;

import dev.lucascaldardo.desafio.itau.junior2024.Docs.EstatisticaControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;

import dev.lucascaldardo.desafio.itau.junior2024.Transacoes.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController implements EstatisticaControllerDoc {



    private final EstatisticasProperties estatisticasProperties;
    private final TransacaoRepository transacaoRepository;

    public EstatisticasController(EstatisticasProperties estatisticasProperties, TransacaoRepository transacaoRepository) {
        this.estatisticasProperties = estatisticasProperties;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public ResponseEntity estatistica(){

        //Log requisicao criado via lombok
        log.info("Calculando estatisticas de transações nos ultimos " + estatisticasProperties.segundos() + " segundos: ");


        //Calcular quantas trancoes acontecem em N segundos
        final var horaInicial = OffsetDateTime.now().minusSeconds(estatisticasProperties.segundos());

        return ResponseEntity.ok(transacaoRepository.estatistica(horaInicial));
    }

}




