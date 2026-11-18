package com.juanpablo.tickets;

import java.time.LocalDateTime;

public record ComentarioResponse(Long id, String texto, LocalDateTime creadoEn) {

	public static ComentarioResponse from(Comentario c) {
		return new ComentarioResponse(c.getId(), c.getTexto(), c.getCreadoEn());
			
	}
}
