package org.udc.parcial.domain.ports.in;

import org.udc.parcial.domain.models.Cita;
import java.util.List;
import java.util.Optional;

public interface AgendarCitaUseCase {
    // Caso de uso principal para procesar el flujo completo de agendamiento
    Cita ejecutarAgendamiento(Cita cita);

    // Métodos complementarios de consulta para el flujo de QA en Postman
    Optional<Cita> buscarPorCodigo(String codigoCita);
    List<Cita> listarTodas();
}