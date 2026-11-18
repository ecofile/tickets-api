package com.juanpablo.tickets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final TicketRepository tickets;
    private final UsuarioRepository usuarios;
    private final ComentarioRepository comentarios;

    public DatosIniciales(TicketRepository tickets, UsuarioRepository usuarios,
                          ComentarioRepository comentarios) {
        this.tickets = tickets;
        this.usuarios = usuarios;
        this.comentarios = comentarios;
    }

    @Override
    public void run(String... args) {
        Usuario ana  = usuarios.save(new Usuario("Ana"));
        Usuario luis = usuarios.save(new Usuario("Luis"));
        Usuario eva  = usuarios.save(new Usuario("Eva"));

        Ticket pc = crear("No arranca el PC del aula 12", ana);
        crear("La impresora de secretaría no imprime", luis);
        crear("El proyector del aula 3 no enciende", eva);
        crear("Sin conexión wifi en la biblioteca", null);

        comentarios.save(new Comentario(pc, "He reiniciado el PC y sigue igual"));
        comentarios.save(new Comentario(pc, "Parece la fuente de alimentación"));
        comentarios.save(new Comentario(pc, "Fuente sustituida, pendiente de probar"));
    }

    private Ticket crear(String titulo, Usuario asignado) {
        Ticket t = new Ticket(titulo);
        t.asignar(asignado);
        return tickets.save(t);
    }
}