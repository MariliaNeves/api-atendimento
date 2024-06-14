package com.atendimento.model.enums;

public enum ServiceStatus {

    CRIADO("Criado"),
    EM_ATENDIMENTO("Em atendimento"),
    AGUARDANDO_ATENDIMENTO("Aguardando atendimento"),
    CONCLUIDO("Concluido");

    private final String description;

    ServiceStatus(String description) {
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