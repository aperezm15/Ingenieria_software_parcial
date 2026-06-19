package org.udc.parcial.domain.ports.in;

import org.springframework.stereotype.Service;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ValueObjects.Horario;
import org.udc.parcial.domain.ports.in.RegistrarMedicoUseCase;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;

@Service // Le dice a Spring que esta es la implementación válida para el controlador
public class RegistrarMedicoUseCaseImpl implements RegistrarMedicoUseCase {

    private final MedicoRepositoryPort medicoRepositoryPort;

    public RegistrarMedicoUseCaseImpl(MedicoRepositoryPort medicoRepositoryPort) {
        this.medicoRepositoryPort = medicoRepositoryPort;
    }

    @Override
    public Medico ejecutarRegistro(Medico medico) {
        // Aquí se conecta con tu adaptador de Supabase que ya funciona
        return medicoRepositoryPort.guardar(medico);
    }

    @Override
    public void asignarHorario(String medicoId, Horario nuevoHorario) {
        // Lógica que haremos en el siguiente issue
    }
}