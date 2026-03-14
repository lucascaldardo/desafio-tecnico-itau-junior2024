package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

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
@Tag(name="Transações",
description="Endpoints responsáveis por criar e adicionar transações em uma lista e também limpa-la" )
public class TransacaoController {

    private TransacaoService transacaoService;
    private TransacaoRepository transacaoRepository;


    public TransacaoController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    @Operation(summary = "Cria novas transações", description = "Recebe uma transação valida e adiciona em uma lista")
    @ApiResponse(responseCode = "201", description = "Transação enviada com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação")
    @ApiResponse(responseCode = "400", description = "Erro inesperado")
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




