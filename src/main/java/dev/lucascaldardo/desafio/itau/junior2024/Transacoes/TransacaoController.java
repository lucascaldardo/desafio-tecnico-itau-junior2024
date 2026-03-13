package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import dev.lucascaldardo.desafio.itau.junior2024.Estatistica.EstatisticasDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;
    private TransacaoRepository transacaoRepository;
    private TransacaoRequest transacaoRequest;

    public TransacaoController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody TransacaoRequest transacaoRequest){

        try{
            transacaoService.validarTransacao(transacaoRequest);
            transacaoRepository.salvarDados(transacaoRequest);

            //Log de sucesso
            log.info("Transação de: {}R$ feita com sucesso. ", transacaoRequest.getValor());
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch(IllegalArgumentException exception){

            //Logo
            log.warn("Falha de validação ao processar transação. Motivo: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        }catch(Exception anotherException){
            log.error("Erro inesperado ao tentar salvar a transação.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletar(){
        transacaoRepository.limparDados();
        log.info("Base de transações limpa com sucesso");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
