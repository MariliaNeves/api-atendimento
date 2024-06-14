package com.atendimento.model.enums;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


public enum Team {

    CARTOES("Cartões"),
    EMPRESTIMOS("Empréstimos"),
    OUTROS_ASSUNTOS("Outros Assuntos");

    private final String description;

    Team(String description) {
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

