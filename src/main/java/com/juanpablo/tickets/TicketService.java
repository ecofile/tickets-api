package com.juanpablo.tickets;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                                    // "Spring, créame y gestióname" (es un bean)
public class TicketService {

    private final TicketRepository repo;    // su DAO

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)         // abre transacción de solo lectura; al salir, la cierra
    public List<TicketResponse> listar() {
        return repo.findAllConAsignado().stream().map(TicketResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TicketResponse obtener(Long id) {
        return repo.findById(id)                          // 1. busca en BD → Optional<Ticket>
                .map(TicketResponse::from)                // 2. si existe, conviértelo a DTO
                .orElseThrow(() -> new TicketNotFoundException(id));  // 3. si no, excepción
    }

    @Transactional
    public TicketResponse crear(String titulo) {
        return TicketResponse.from(repo.save(new Ticket(titulo)));
    }
}