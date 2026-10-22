package com.juanpablo.tickets;

import java.time.LocalDateTime;

public record TicketResponse(Long id, String titulo, Ticket.Estado estado, LocalDateTime creadoEn) {

    public static TicketResponse from(Ticket t) {
        return new TicketResponse(t.getId(), t.getTitulo(), t.getEstado(), t.getCreadoEn());
    }
}