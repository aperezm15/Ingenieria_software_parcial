package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.stereotype.Component;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ValueObjects.Horario;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // Anotación de Spring para que pueda ser inyectado más adelante
public class MedicoRepositoryAdapter implements MedicoRepositoryPort {

    private final SpringDataMedicoRepository repository;

    // Inyección por constructor
    public MedicoRepositoryAdapter(SpringDataMedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medico guardar(Medico medico) {
        // 1. Mapear de Dominio (Medico) a Infraestructura (MedicoEntity)
        List<HorarioEntity> horariosEntity = medico.getAgendaDisponibilidad().stream()
                .map(h -> new HorarioEntity(h.fechaHoraInicio(), h.fechaHoraFin(), h.disponible()))
                .collect(Collectors.toList());

        MedicoEntity entityAsalvar = new MedicoEntity(
                medico.getId(),
                medico.getNombreCompleto(),
                medico.getEspecialidad(),
                horariosEntity
        );

        // 2. Guardar en Supabase a través de Spring Data
        MedicoEntity entityGuardada = repository.save(entityAsalvar);

        // 3. Convertir de vuelta al modelo de Dominio para retornar el resultado corregido
        return mapearADominio(entityGuardada);
    }

    @Override
    public Optional<Medico> buscarPorId(String id) {
        return repository.findById(id).map(this::mapearADominio);
    }

    // Método helper privado para convertir de Entidad JPA a Modelo de Dominio Puro
    private Medico mapearADominio(MedicoEntity entity) {
        Medico medicoDomain = new Medico(entity.getId(), entity.getNombreCompleto(), entity.getEspecialidad());

        if (entity.getAgendaDisponibilidad() != null) {
            entity.getAgendaDisponibilidad().forEach(h ->
                    medicoDomain.agregarHorarioAlDominio(new Horario(h.getFechaHoraInicio(), h.getFechaHoraFin(), h.isDisponible()))
            );
        }
        return medicoDomain;
    }
}