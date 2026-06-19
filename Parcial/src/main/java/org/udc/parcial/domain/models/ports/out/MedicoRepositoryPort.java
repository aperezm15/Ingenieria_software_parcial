package org.udc.parcial.domain.models.ports.out;

import org.udc.parcial.domain.models.Medico;
import java.util.Optional;

public interface MedicoRepositoryPort {
    // Guardar o actualizar un médico en la base de datos (Supabase)
    Medico guardar(Medico medico);

    // Buscar un médico por su ID de negocio (Cédula)
    Optional<Medico> buscarPorId(String id);
}