package com.juanpablo.tickets;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.ABIERTO;

    private LocalDateTime creadoEn = LocalDateTime.now();

    protected Ticket() { }                       // JPA lo exige

    public Ticket(String titulo) { this.titulo = titulo; }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Estado getEstado() { return estado; }
    public LocalDateTime getCreadoEn() { return creadoEn; }

    public enum Estado { ABIERTO, EN_CURSO, CERRADO }
}