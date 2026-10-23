package com.juanpablo.tickets;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController                         // "esta clase atiende peticiones y devuelve datos (JSON)"
@RequestMapping("/api/tickets")         // todas sus URLs empiezan por /api/tickets
public class TicketController {

    private final TicketService service;              // lo que necesita para trabajar

    public TicketController(TicketService service) {  // Spring se lo da ya creado
        this.service = service;
    }

    @GetMapping
    public List<TicketResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")                               // atiende GET /api/tickets/ALGO
    public TicketResponse obtener(@PathVariable Long id) {   // "ALGO" se mete en id (ya convertido a Long)
        return service.obtener(id);                    // delega y devuelve lo que le den
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse crear(@Valid @RequestBody CrearTicketRequest req) {
        return service.crear(req.titulo());
    }

    public record CrearTicketRequest(@NotBlank @Size(max = 120) String titulo) { }
}