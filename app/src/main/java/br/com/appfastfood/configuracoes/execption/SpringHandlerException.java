package br.com.appfastfood.configuracoes.execption;

import br.com.appfastfood.cliente.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.produto.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SpringHandlerException extends ResponseEntityExceptionHandler {

    private Log logger;

    public SpringHandlerException(Log logger) {
        this.logger = logger;
    }

    @ExceptionHandler(value = CamposObrigatorioException.class)
    public ResponseEntity<Object> handleCamposObrigatorioException(CamposObrigatorioException e) {
        RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.aviso(jsonExcecao.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
    }

    @ExceptionHandler(value = UriImagemFormatoInvalidoException.class)
    public ResponseEntity<Object> handleUriImagemFormatoInvalidoException(UriImagemFormatoInvalidoException e) {
        RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.aviso(jsonExcecao.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
    }

    @ExceptionHandler(value = UriImagemObrigatorioException.class)
    public ResponseEntity<Object> handleUriImagemObrigatorioException(UriImagemObrigatorioException e) {
        RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.aviso(jsonExcecao.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
    }

    @ExceptionHandler(value = CategoriaNaoEncontradaException.class)
    public ResponseEntity<Object> handleCategoriaNaoEncontradaExceptionException(CategoriaNaoEncontradaException e) {
        RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.aviso(jsonExcecao.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
    }

    @ExceptionHandler(value = IDNaoEncontradoException.class)
    public ResponseEntity<Object> handleIDNaoEncontradoException(IDNaoEncontradoException e) {
        RequisicaoExcecao jsonExcecao = new RequisicaoExcecao(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.aviso(jsonExcecao.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonExcecao);
    }
}
