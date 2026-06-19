package org.udc.parcial.domain.models;

public class Paciente {
    private final String id; // Cédula o Documento Único de Identidad (ID de negocio)
    private final String nombreCompleto;
    private final String correo;
    private final String telefono;

    // Constructor con la validación de identidad exigida por la HU
    public Paciente(String id, String nombreCompleto, String correo, String telefono) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Error de negocio: El documento de identidad del paciente no puede estar vacío.");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("Error de negocio: El nombre completo del paciente es obligatorio.");
        }
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters limpios para mantener la inmutabilidad del dominio
    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
}