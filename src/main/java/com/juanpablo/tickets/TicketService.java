package com.juanpablo.tickets;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> listar() {
        return repo.findAllConAsignado().stream().map(TicketResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TicketResponse obtener(Long id) {
        return repo.findById(id)
                .map(TicketResponse::from)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Transactional
    public TicketResponse crear(String titulo) {
        return TicketResponse.from(repo.save(new Ticket(titulo)));
    }
}