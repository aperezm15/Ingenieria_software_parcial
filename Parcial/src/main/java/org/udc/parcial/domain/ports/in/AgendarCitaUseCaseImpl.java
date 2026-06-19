package org.udc.parcial.domain.ports.in;

import org.springframework.stereotype.Service;
import org.udc.parcial.domain.models.Cita;
import org.udc.parcial.domain.ports.out.CitaRepositoryPort;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;
import org.udc.parcial.domain.ports.out.PacienteRepositoryPort;

import java.util.List;
import java.util.Optional;

@Service
public class AgendarCitaUseCaseImpl implements AgendarCitaUseCase {

    private final CitaRepositoryPort citaRepositoryPort;
    private final MedicoRepositoryPort medicoRepositoryPort;
    private final PacienteRepositoryPort pacienteRepositoryPort;

    // Inyección de todos los puertos necesarios para cruzar los datos
    public AgendarCitaUseCaseImpl(CitaRepositoryPort citaRepositoryPort,
                                  MedicoRepositoryPort medicoRepositoryPort,
                                  PacienteRepositoryPort pacienteRepositoryPort) {
        this.citaRepositoryPort = citaRepositoryPort;
        this.medicoRepositoryPort = medicoRepositoryPort;
        this.pacienteRepositoryPort = pacienteRepositoryPort;
    }

    @Override
    public Cita ejecutarAgendamiento(Cita cita) {
        // 1. Regla de Negocio: Validar existencia del Paciente
        pacienteRepositoryPort.buscarPorId(cita.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Error de negocio: El paciente con ID " + cita.getPacienteId() + " no está registrado en el sistema."));

        // 2. Regla de Negocio: Validar existencia del Médico
        medicoRepositoryPort.buscarPorId(cita.getMedicoId())
                .orElseThrow(() -> new IllegalArgumentException("Error de negocio: El médico con ID " + cita.getMedicoId() + " no está registrado en el sistema."));

        // 3. Control de Concurrencia Crítico: Validar solapamiento de horarios
        boolean isSolapado = citaRepositoryPort.existeCitaSolapada(
                cita.getMedicoId(),
                cita.getFechaHoraInicio(),
                cita.getFechaHoraFin()
        );

        if (isSolapado) {
            throw new IllegalStateException("Error de negocio: El médico ya cuenta con una cita agendada o en conflicto dentro del rango horario seleccionado.");
        }

        // 4. Si pasa todos los filtros de integridad, se persiste de forma segura
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