package com.juanpablo.tickets;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<TicketResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TicketResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse crear(@Valid @RequestBody CrearTicketRequest req) {
        return service.crear(req.titulo());
    }

    public record CrearTicketRequest(@NotBlank @Size(max = 120) String titulo) { }
}