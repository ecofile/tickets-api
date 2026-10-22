package com.juanpablo.tickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEstado(Ticket.Estado estado);   // Spring genera la consulta
}