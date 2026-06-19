package org.udc.parcial.domain.ports.out;

import org.udc.parcial.domain.models.Cita;
import java.util.List;
import java.util.Optional;

public interface CitaRepositoryPort {
    Cita guardar(Cita cita);
    Optional<Cita> buscarPorId(String codigoCita);
    List<Cita> listarTodas();

    // Método clave para verificar concurrencia: valida si el médico ya tiene otra cita activa en ese rango horario
    boolean existeCitaSolapada(String medicoId, java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
}