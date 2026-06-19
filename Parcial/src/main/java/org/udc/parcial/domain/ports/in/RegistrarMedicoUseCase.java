package org.udc.parcial.domain.ports.in;

import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ValueObjects.Horario;

public interface RegistrarMedicoUseCase {
    // Caso de uso 1: Registrar un médico en el sistema
    Medico ejecutarRegistro(Medico medico);

    // Caso de uso 2: Agregar un horario disponible a la agenda de un médico existente
    void asignarHorario(String medicoId, Horario nuevoHorario);
}