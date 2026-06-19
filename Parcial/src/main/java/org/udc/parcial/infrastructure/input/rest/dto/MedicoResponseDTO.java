package org.udc.parcial.infrastructure.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDTO {
    private String id;
    private String nombreCompleto;
    private String specialty;
    private List<HorarioDTO> agendaDisponibilidad;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HorarioDTO {
        private java.time.LocalDateTime fechaHoraInicio;
        private java.time.LocalDateTime fechaHoraFin;
        private boolean disponible;
    }
}