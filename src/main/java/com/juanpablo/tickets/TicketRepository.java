package com.juanpablo.tickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEstado(Ticket.Estado estado);   // Spring genera la consulta
    
    @Query("select t from Ticket t left join fetch t.asignado")
    List<Ticket> findAllConAsignado();
}