package br.com.appfastfood.interfaces.usecases;

import java.util.Optional;
import java.util.UUID;

public interface ConsultarStatusPagamentoUseCasePort {
    Boolean execute(UUID pedidoId);
}
