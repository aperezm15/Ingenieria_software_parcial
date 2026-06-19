package org.udc.parcial.domain.ports.out;

import org.udc.parcial.domain.models.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepositoryPort {
    // Guardar o actualizar un médico en la base de datos (Supabase)
    Medico guardar(Medico medico);

    // Buscar un médico por su ID de negocio (Cédula)
    Optional<Medico> buscarPorId(String id);

    // Listar Todos los medicos
    List<Medico> listarTodos();
}