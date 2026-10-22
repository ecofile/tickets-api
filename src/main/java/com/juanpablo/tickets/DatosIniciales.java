package com.juanpablo.tickets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final TicketRepository tickets;
    private final UsuarioRepository usuarios;

    public DatosIniciales(TicketRepository tickets, UsuarioRepository usuarios) {
        this.tickets = tickets;
        this.usuarios = usuarios;
    }

    @Override
    public void run(String... args) {
        Usuario ana  = usuarios.save(new Usuario("Ana"));
        Usuario luis = usuarios.save(new Usuario("Luis"));
        Usuario eva  = usuarios.save(new Usuario("Eva"));

        crear("No arranca el PC del aula 12", ana);
        crear("La impresora de secretaría no imprime", luis);
        crear("El proyector del aula 3 no enciende", eva);
        crear("Sin conexión wifi en la biblioteca", null);
    }

    private void crear(String titulo, Usuario asignado) {
        Ticket t = new Ticket(titulo);
        t.asignar(asignado);
        tickets.save(t);
    }
}