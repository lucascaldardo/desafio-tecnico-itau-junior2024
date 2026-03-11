package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private TransacaoService transacaoService;
    private TransacaoRepository transacaoRepository;

    public TransacaoController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.transacaoRepository = transacaoRepository;
    }


    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody TransacaoRequest transacaoRequest){

        try{
            transacaoService.validarTransacao(transacaoRequest);
            transacaoRepository.salvarDados(transacaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch(IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        }catch(Exception anotherException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletar(){
        transacaoRepository.limparDados();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
