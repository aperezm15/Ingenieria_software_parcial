package org.udc.parcial.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {
    private String id;
    private String nombreCompleto;
    private String correo;
    private String telefono;
}