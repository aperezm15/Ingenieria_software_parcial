package org.udc.parcial.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {
    private String id; // Cédula o documento
    private String nombreCompleto;
    private String correo;
    private String telefono;
}