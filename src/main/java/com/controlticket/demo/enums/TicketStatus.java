package com.controlticket.demo.enums;

public enum TicketStatus {
    PAID("Pago"),
    OPEN("Em aberto"),
    OVERDUE("Vencido"),
    CANCELED("Cancelado");

    private String description;

    TicketStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
