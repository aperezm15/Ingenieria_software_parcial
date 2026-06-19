package org.udc.parcial.domain.ports.out;

import org.udc.parcial.domain.models.Paciente;
import java.util.List;
import java.util.Optional;

public interface PacienteRepositoryPort {
    // Operación de persistencia para salvar o actualizar
    Paciente guardar(Paciente paciente);

    // Operaciones de recuperación de datos
    Optional<Paciente> buscarPorId(String id);
    List<Paciente> listarTodos();
}