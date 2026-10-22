package com.juanpablo.tickets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final TicketRepository repo;

    public DatosIniciales(TicketRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Ticket("No arranca el PC del aula 12"));
        repo.save(new Ticket("La impresora de secretaría no imprime"));
    }
}