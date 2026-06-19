package org.udc.parcial.domain.ports.in;

import org.udc.parcial.domain.models.Paciente;
import java.util.List;
import java.util.Optional;

public interface RegistrarPacienteUseCase {
    // Caso de uso principal: Registrar al paciente por primera vez
    Paciente ejecutarRegistro(Paciente paciente);

    // Métodos de consulta complementarios para las pruebas de integración (QA)
    Optional<Paciente> buscarPorId(String id);
    List<Paciente> listarTodos();
}