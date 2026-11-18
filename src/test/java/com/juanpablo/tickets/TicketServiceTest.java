package com.juanpablo.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)      // activa Mockito en este test
class TicketServiceTest {

    @Mock TicketRepository repo;                      // repositorio falso
    @Mock ComentarioRepository comentarioRepo;        // repositorio falso
    @InjectMocks TicketService service;               // el servicio real, con los falsos dentro

    @Test
    void comentarios_siElTicketNoExiste_lanzaExcepcion() {
        // Given: el repositorio dice que el ticket 99 no existe
        when(repo.existsById(99L)).thenReturn(false);

        // When / Then: al pedir sus comentarios, salta la excepción
        assertThrows(TicketNotFoundException.class, () -> service.comentarios(99L));
    }

    @Test
    void comentarios_siElTicketNoTieneComentarios_devuelveListaVacia() {
        // Given: el ticket 2 existe y su repositorio devuelve una lista vacía
        when(repo.existsById(2L)).thenReturn(true);
        when(comentarioRepo.findByTicketIdOrderByCreadoEnAsc(2L)).thenReturn(List.of());

        // When
        List<ComentarioResponse> resultado = service.comentarios(2L);

        // Then
        assertTrue(resultado.isEmpty());
    }

    @Test
    void comentarios_siElTicketTieneComentarios_losDevuelveComoDto() {
        // Given: un ticket y un comentario de prueba
        Ticket ticket = new Ticket("Prueba");
        Comentario comentario = new Comentario(ticket, "Hola");

        when(repo.existsById(1L)).thenReturn(true);
        when(comentarioRepo.findByTicketIdOrderByCreadoEnAsc(1L)).thenReturn(List.of(comentario));

        // When
        List<ComentarioResponse> resultado = service.comentarios(1L);

        // Then: hay 1 comentario y el texto se ha copiado al DTO
        assertEquals(1, resultado.size());
        assertEquals("Hola", resultado.get(0).texto());
    }
}