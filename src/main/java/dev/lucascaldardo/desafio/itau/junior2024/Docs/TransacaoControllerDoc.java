package dev.lucascaldardo.desafio.itau.junior2024.Docs;

import dev.lucascaldardo.desafio.itau.junior2024.Transacoes.TransacaoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name="Transações",
        description="Endpoints responsáveis por criar e adicionar transações em uma fila e também limpa-la" )
public interface TransacaoControllerDoc {

    @Operation(summary = "Cria novas transações", description = "Recebe uma transação valida e adiciona em uma fila")
    @ApiResponse(responseCode = "201", description = "Transação enviada com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação")
    @ApiResponse(responseCode = "400", description = "Erro inesperado")

    ResponseEntity<Void> adicionar(@Valid @RequestBody TransacaoRequest transacaoRequest);

    @Operation(summary = "Deleta todas as transações", description = "Deleta todas as transações feitas em todo o periodo")

    public ResponseEntity deletar();
}
