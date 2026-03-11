package dev.lucascaldardo.desafio.itau.junior2024.Estatistica;

import dev.lucascaldardo.desafio.itau.junior2024.Transacoes.TransacaoRepository;
import dev.lucascaldardo.desafio.itau.junior2024.Transacoes.TransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;


@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasProperties estatisticasProperties;
    private final TransacaoRepository transacaoRepository;

    public EstatisticasController(EstatisticasProperties estatisticasProperties, TransacaoRepository transacaoRepository) {
        this.estatisticasProperties = estatisticasProperties;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public ResponseEntity estatistica(){
        final var horaInicial = OffsetDateTime.now().minusSeconds(estatisticasProperties.segundos());

        return ResponseEntity.ok(transacaoRepository.estatistica(horaInicial));
    }

}




