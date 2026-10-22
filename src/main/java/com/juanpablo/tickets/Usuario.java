package com.juanpablo.tickets;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    protected Usuario() { }

    public Usuario(String nombre) { this.nombre = nombre; }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}