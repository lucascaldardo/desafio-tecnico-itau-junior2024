package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import dev.lucascaldardo.desafio.itau.junior2024.Docs.TransacaoControllerDoc;
import dev.lucascaldardo.desafio.itau.junior2024.Estatistica.EstatisticasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacaoController implements TransacaoControllerDoc {

    private TransacaoService transacaoService;
    private TransacaoRepository transacaoRepository;


    public TransacaoController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody TransacaoRequest transacaoRequest){


        transacaoService.adicionarTransacao(transacaoRequest);

        //Log de sucesso
        log.info("Transação de: {}R$ feita com sucesso. ", transacaoRequest.getValor());
        return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @DeleteMapping
        public ResponseEntity deletar(){
            transacaoRepository.limparDados();
            log.info("Base de transações limpa com sucesso");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
}




