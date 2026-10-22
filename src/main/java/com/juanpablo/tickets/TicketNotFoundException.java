package com.juanpablo.tickets;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long id) {
        super("El ticket " + id + " no existe");
    }
}