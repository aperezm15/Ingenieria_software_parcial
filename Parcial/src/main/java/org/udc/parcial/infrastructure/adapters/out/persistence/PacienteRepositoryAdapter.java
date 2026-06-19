package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.stereotype.Component;
import org.udc.parcial.domain.models.Paciente;
import org.udc.parcial.domain.ports.out.PacienteRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PacienteRepositoryAdapter implements PacienteRepositoryPort {

    private final SpringDataPacienteRepository repository;

    // Inyección limpia por constructor
    public PacienteRepositoryAdapter(SpringDataPacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        // 1. Mapear de Dominio (Paciente) a Infraestructura (PacienteEntity)
        PacienteEntity entityASalvar = new PacienteEntity(
                paciente.getId(),
                paciente.getNombreCompleto(),
                paciente.getCorreo(),
                paciente.getTelefono()
        );

        // 2. Guardar en Supabase a través de Spring Data
        PacienteEntity entityGuardada = repository.save(entityASalvar);

        // 3. Convertir de vuelta al modelo de Dominio para retornar el resultado de forma limpia
        return mapearADominio(entityGuardada);
    }

    @Override
    public Optional<Paciente> buscarPorId(String id) {
        return repository.findById(id).map(this::mapearADominio);
    }

    @Override
    public List<Paciente> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearADominio)
                .collect(Collectors.toList());
    }

    // Método helper privado para convertir de Entidad JPA a Modelo de Dominio Puro
    private Paciente mapearADominio(PacienteEntity entity) {
        return new Paciente(
                entity.getId(),
                entity.getNombreCompleto(),
                entity.getCorreo(),
                entity.getTelefono()
        );
    }
}