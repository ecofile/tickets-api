package com.juanpablo.tickets;

import java.time.LocalDateTime;

public record TicketResponse(Long id, String titulo, Ticket.Estado estado,
                             LocalDateTime creadoEn, String asignado) {

    public static TicketResponse from(Ticket t) {
        String nombre = t.getAsignado() == null ? null : t.getAsignado().getNombre();
        return new TicketResponse(t.getId(), t.getTitulo(), t.getEstado(), t.getCreadoEn(), nombre);
    }
}