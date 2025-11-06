package com.dev.MealFood.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PratoNaoEncontradoException extends GlobalMealException {

    private String nomePrato;

    public PratoNaoEncontradoException(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Prato nao encontrado!!");
        pb.setDetail("verifique com nosso garçom se temos o prato: " + nomePrato);
        return pb;
    }
}

