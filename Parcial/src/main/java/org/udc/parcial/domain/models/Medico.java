package org.udc.parcial.domain.models;

import org.udc.parcial.domain.ValueObjects.Horario;
import java.util.ArrayList;
import java.util.List;

public class Medico {
    private final String id; // Cédula o Registro Médico (ID único de negocio)
    private final String nombreCompleto;
    private final String especialidad; // Cambiado a 'especialidad' para evitar teléfonos rotos
    private final List<Horario> agendaDisponibilidad;

    // Constructor limpio y consistente
    public Medico(String id, String nombreCompleto, String especialidad) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El ID del médico no puede estar vacío.");
        if (especialidad == null || especialidad.isBlank()) throw new IllegalArgumentException("La especialidad es obligatoria.");

        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.agendaDisponibilidad = new ArrayList<>();
    }

    public void agregarHorarioAlDominio(Horario nuevoHorario) {
        for (Horario h : agendaDisponibilidad) {
            // Fórmula lógica para detectar si dos rangos de tiempo se cruzan
            boolean seTraslapa = nuevoHorario.fechaHoraInicio().isBefore(h.fechaHoraFin()) &&
                    nuevoHorario.fechaHoraFin().isAfter(h.fechaHoraInicio());

            if (seTraslapa) {
                throw new IllegalStateException("Error de negocio: El horario que intenta asignar se cruza con uno existente para este médico.");
            }
        }
        this.agendaDisponibilidad.add(nuevoHorario);
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEspecialidad() { return especialidad; }

    public List<Horario> getAgendaDisponibilidad() {
        return new ArrayList<>(agendaDisponibilidad); // Retorna una copia para evitar modificaciones externas ilegales
    }
}