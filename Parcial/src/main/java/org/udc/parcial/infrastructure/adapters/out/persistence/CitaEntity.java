package org.udc.parcial.infrastructure.adapters.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaEntity {

    @Id
    private String codigoCita; // Llave primaria (UUID o código autogenerado)

    private String pacienteId; // ID del paciente (FK conceptual)
    private String medicoId;   // ID del médico (FK conceptual)
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String estado;
}