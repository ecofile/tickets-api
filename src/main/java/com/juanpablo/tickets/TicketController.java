package com.juanpablo.tickets;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository repo;

    public TicketController(TicketRepository repo) {   // inyección por constructor
        this.repo = repo;
    }

    @GetMapping
    public List<Ticket> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Ticket obtener(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ticket " + id + " no existe"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket crear(@Valid @RequestBody CrearTicketRequest req) {
        return repo.save(new Ticket(req.titulo()));
    }

    public record CrearTicketRequest(@NotBlank @Size(max = 120) String titulo) { }
}