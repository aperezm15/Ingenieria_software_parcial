package org.udc.parcial.domain.models;

import java.time.LocalDateTime;

public class Cita {
    private final String codigoCita; // ID único de la cita
    private final String pacienteId; // Cédula del paciente asociado
    private final String medicoId;   // Cédula del médico asociado
    private final LocalDateTime fechaHoraInicio;
    private final LocalDateTime fechaHoraFin;
    private String estado; // EJ: "AGENDADA", "CANCELADA"

    // Constructor con validaciones estructurales de negocio
    public Cita(String codigoCita, String pacienteId, String medicoId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String estado) {
        if (codigoCita == null || codigoCita.isBlank()) throw new IllegalArgumentException("El código de la cita no puede estar vacío.");
        if (pacienteId == null || pacienteId.isBlank()) throw new IllegalArgumentException("El ID del paciente es obligatorio para agendar.");
        if (medicoId == null || medicoId.isBlank()) throw new IllegalArgumentException("El ID del médico es obligatorio para agendar.");
        if (fechaHoraInicio == null || fechaHoraFin == null) throw new IllegalArgumentException("Los rangos de fecha y hora no pueden ser nulos.");
        if (fechaHoraInicio.isAfter(fechaHoraFin)) throw new IllegalArgumentException("La fecha de inicio de la cita no puede ser posterior a la de fin.");

        this.codigoCita = codigoCita;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = (estado == null || estado.isBlank()) ? "AGENDADA" : estado;
    }

    // Método de negocio para cancelar la cita
    public void cancelarCita() {
        this.estado = "CANCELADA";
    }

    // Getters limpios
    public String getCodigoCita() { return codigoCita; }
    public String getPacienteId() { return pacienteId; }
    public String getMedicoId() { return medicoId; }
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public String getEstado() { return estado; }
}