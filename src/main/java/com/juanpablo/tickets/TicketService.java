package com.juanpablo.tickets;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                                    // "Spring, créame y gestióname" (es un bean)
public class TicketService {

    private final TicketRepository repo;    // su DAO
    private final ComentarioRepository comentarioRepo;
    
    public TicketService(TicketRepository repo, ComentarioRepository comentarioRepo) {
        this.repo = repo;
        this.comentarioRepo = comentarioRepo;
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
    
    @Transactional(readOnly = true)
    public List<ComentarioResponse> comentarios(Long ticketId) {

        // 1. Si el ticket no existe → 404
        if (!repo.existsById(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }

        // 2. Busca sus comentarios y conviértelos a DTO
        return comentarioRepo.findByTicketIdOrderByCreadoEnAsc(ticketId)
                .stream()
                .map(ComentarioResponse::from)
                .toList();
    }

    @Transactional
    public TicketResponse crear(String titulo) {
        return TicketResponse.from(repo.save(new Ticket(titulo)));
    }
}