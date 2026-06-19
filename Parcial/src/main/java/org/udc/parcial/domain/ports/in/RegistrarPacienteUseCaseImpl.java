package org.udc.parcial.domain.ports.in;

import org.springframework.stereotype.Service;
import org.udc.parcial.domain.models.Paciente;
import org.udc.parcial.domain.ports.out.PacienteRepositoryPort;

import java.util.List;
import java.util.Optional;

@Service // Permite que Spring lo reconozca para inyectarlo en el controlador
public class RegistrarPacienteUseCaseImpl implements RegistrarPacienteUseCase {

    private final PacienteRepositoryPort pacienteRepositoryPort;

    public RegistrarPacienteUseCaseImpl(PacienteRepositoryPort pacienteRepositoryPort) {
        this.pacienteRepositoryPort = pacienteRepositoryPort;
    }

    @Override
    public Paciente ejecutarRegistro(Paciente paciente) {
        return pacienteRepositoryPort.guardar(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(String id) {
        return pacienteRepositoryPort.buscarPorId(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepositoryPort.listarTodos();
    }
}