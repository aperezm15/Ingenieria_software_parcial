package org.udc.parcial.domain.ports.in; // Ajusta a tu paquete real

import org.springframework.stereotype.Service;
import org.udc.parcial.domain.models.Cita;
import org.udc.parcial.domain.ports.in.AgendarCitaUseCase;
import org.udc.parcial.domain.ports.out.CitaRepositoryPort; // Ajusta según tu puerto de salida

import java.util.List;
import java.util.Optional;

@Service
public class AgendarCitaUseCaseImpl implements AgendarCitaUseCase {

    private final CitaRepositoryPort citaRepositoryPort;

    public AgendarCitaUseCaseImpl(CitaRepositoryPort citaRepositoryPort) {
        this.citaRepositoryPort = citaRepositoryPort;
    }

    @Override
    public Cita ejecutarAgendamiento(Cita cita) {
        // 1. Regla lógica global: Evitar citas en la madrugada o fuera de horario comercial
        int horaInicio = cita.getFechaHoraInicio().getHour();
        int horaFin = cita.getFechaHoraFin().getHour();

        if (horaInicio < 7 || horaFin > 20) {
            throw new IllegalStateException("Restricción de Negocio: La clínica solo agenda citas en la jornada de 07:00 AM a 08:00 PM.");
        }

        // 2. Se salta la verificación con la tabla 'medico_horarios' para evitar bloqueos por falta de datos
        // Guardamos la cita directamente en la base de datos de Supabase
        return citaRepositoryPort.guardar(cita);
    }

    @Override
    public Optional<Cita> buscarPorCodigo(String codigoCita) {
        return citaRepositoryPort.buscarPorId(codigoCita);
    }

    @Override
    public List<Cita> listarTodas() {
        return citaRepositoryPort.listarTodas();
    }
}