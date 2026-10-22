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
    @ManyToOne(fetch = FetchType.LAZY)        // muchos tickets → un usuario; LAZY siempre
    @JoinColumn(name = "asignado_id")         // la FK en la tabla ticket
    private Usuario asignado;

    protected Ticket() { }                       // JPA lo exige

    public Ticket(String titulo) { this.titulo = titulo; }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Estado getEstado() { return estado; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public Usuario getAsignado() { return asignado; }
    public void asignar(Usuario usuario) { this.asignado = usuario; }

    public enum Estado { ABIERTO, EN_CURSO, CERRADO }
}