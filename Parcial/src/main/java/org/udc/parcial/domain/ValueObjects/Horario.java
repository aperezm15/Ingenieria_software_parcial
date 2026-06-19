package org.udc.parcial.domain.ValueObjects;

import java.time.LocalDateTime;

public record Horario(
        LocalDateTime fechaHoraInicio,
        LocalDateTime fechaHoraFin,
        boolean disponible // true si está libre para agendar, false si ya está ocupado
) {
    // Validación de negocio nativa: La hora de inicio nunca puede ser posterior a la de fin
    public Horario {
        if (fechaHoraInicio == null || fechaHoraFin == null) {
            throw new IllegalArgumentException("Las fechas y horas no pueden ser nulas.");
        }
        if (fechaHoraInicio.isAfter(fechaHoraFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
    }
}