package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TransacoesControllerAdvice {

    // Captura os erros das anotações @Valid (ex: @PositiveOrZero, @PastOrPresent)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Erro de validação (422): {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    // Captura os erros manuais que você lançou no seu TransacaoService com "throw new IllegalArgumentException"
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Erro de validação manual (422): {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    // Opcional, mas recomendado: Garante o 400 para JSON malformado (ex: passar uma letra onde deveria ser número)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Erro de requisição malformada (400): {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
