package org.udc.parcial.domain.models;

import org.udc.parcial.domain.models.ValueObjects.Horario;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private final String id; // Cédula o Registro Médico (ID único de negocio)
    private final String nombreCompleto;
    private final String specialty; // Especialidad médica
    private final List<Horario> agendaDisponibilidad;

    // Constructor limpio
    public Medico(String id, String nombreCompleto, String specialty) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El ID del médico no puede estar vacío.");
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.specialty = specialty;
        this.agendaDisponibilidad = new ArrayList<>();
    }

    /**
     * REGLA DE NEGOCIO CRÍTICA (HU-01):
     * Permite agregar un horario a la agenda del médico asegurando que no se traslape con otro.
     */
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

    // Getters manuales (Java Puro, protegiendo la encapsulación de la lista)
    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getSpecialty() { return specialty; }
    public List<Horario> getAgendaDisponibilidad() {
        return new ArrayList<>(agendaDisponibilidad); // Retorna una copia para evitar modificaciones externas ilegales
    }
}