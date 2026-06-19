package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.stereotype.Component;
import org.udc.parcial.domain.models.Cita;
import org.udc.parcial.domain.ports.out.CitaRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CitaRepositoryAdapter implements CitaRepositoryPort {

    private final SpringDataCitaRepository repository;

    public CitaRepositoryAdapter(SpringDataCitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cita guardar(Cita cita) {
        CitaEntity entity = new CitaEntity(
                cita.getCodigoCita(),
                cita.getPacienteId(),
                cita.getMedicoId(),
                cita.getFechaHoraInicio(),
                cita.getFechaHoraFin(),
                cita.getEstado()
        );
        CitaEntity guardada = repository.save(entity);
        return mapearADominio(guardada);
    }

    @Override
    public Optional<Cita> buscarPorId(String codigoCita) {
        return repository.findById(codigoCita).map(this::mapearADominio);
    }

    @Override
    public List<Cita> listarTodas() {
        return repository.findAll().stream()
                .map(this::mapearADominio)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeCitaSolapada(String medicoId, LocalDateTime inicio, LocalDateTime fin) {
        return repository.existsOverlappingCita(medicoId, inicio, fin);
    }

    // Helper privado para transformar de Entidad JPA a Modelo Puro de Dominio
    private Cita mapearADominio(CitaEntity entity) {
        return new Cita(
                entity.getCodigoCita(),
                entity.getPacienteId(),
                entity.getMedicoId(),
                entity.getFechaHoraInicio(),
                entity.getFechaHoraFin(),
                entity.getEstado()
        );
    }
}