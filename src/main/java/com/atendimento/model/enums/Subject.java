package com.atendimento.model.enums;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


public enum Subject {
    PROBLEMAS_COM_CARTAO("Problemas com cartão"),
    CONTRATACAO_DE_EMPRESTIMO("Contratação de empréstimo"),
    OUTROS("Outros");

    private final String description;

    Subject(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

