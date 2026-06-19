package org.udc.parcial.infrastructure.adapters.out.persistence;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable // Permite incrustar esta entidad como una colección dentro de MedicoEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioEntity {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private boolean disponible;
}