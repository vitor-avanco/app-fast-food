package br.com.appfastfood.api;

import br.com.appfastfood.api.requests.ConfirmacaoPagamentoRequest;
import br.com.appfastfood.controllers.PagamentoController;
import br.com.appfastfood.presenters.PagamentoPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "API de Pagamentos")
public class PagamentoApi {

    private final PagamentoController pagamentoController;

    public PagamentoApi(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Operation(summary = "Consultar status de pagamento", description = "Consulta o status de pagamento a partir do id do pedido.")
    @GetMapping("/pagamentos/{pedidoId}")
    public ResponseEntity<PagamentoPresenter> consultarStatusPagamento(@PathVariable UUID pedidoId) {
        return ResponseEntity.ok(this.pagamentoController.consultarStatusPagamento(pedidoId));
    }

    @Operation(summary = "Receber confirmaçào de pagamento (Webhook)",
            description = "Webhook para receber a confirmação de pagamento MercadoPago.<br>" +
                    "Somente os parâmetros action e data.id serão processados.<br>" +
                    "- action: Ação a ser processada. Somente a confirmação de pagamento é aceita no momento. Exemplo: payment.created<br>" +
                    "- data.id: O id de pagamento registrado no checkout de pedido.")
    @PostMapping("/pagamentos")
    public ResponseEntity<String> receberConfirmacaoPagamento(@RequestBody ConfirmacaoPagamentoRequest confirmacaoPagamentoRequest) {
        return ResponseEntity.ok(this.pagamentoController.receberConfirmacaoPagamento(confirmacaoPagamentoRequest.toDomain()));
    }
}
