package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacao")
public class TransacoesController {

    private TransacoesService transacoesService;

    public TransacoesController(@RequestBody TransacoesService transacoesService) {
        this.transacoesService = transacoesService;
    }

    @PostMapping
    public ResponseEntity adicionar(@RequestBody TransacoesRequest transacoesRequest){

        try{
            transacoesService.validarTransacao(transacoesRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch(IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

}
