package org.udc.parcial.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequestDTO {
    private String id; // Tu cédula / registro médico
    private String nombreCompleto;
    private String specialty; // Mantiene compatibilidad con el JSON del frontend
}