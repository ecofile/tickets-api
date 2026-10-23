package com.juanpablo.tickets;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String texto;

    private LocalDateTime creadoEn = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)                    // ← lo que faltaba: ES una relación
    @JoinColumn(name = "ticket_id", nullable = false)     // FK obligatoria
    private Ticket ticket;                                // ← nombre con significado

    protected Comentario() { }

    public Comentario(Ticket ticket, String texto) {      // ← sin ticket no se puede crear
        this.ticket = ticket;
        this.texto = texto;
    }

    public Long getId() { return id; }
    public String getTexto() { return texto; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public Ticket getTicket() { return ticket; }
}