package org.udc.parcial.infrastructure.adapters.out.persistence;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEntity {

    @Id
    private String id; // Se usará la cédula/registro como Primary Key en Supabase

    private String nombreCompleto;
    private String especialidad; // Mapea directo a la columna 'especialidad' de Supabase

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medico_horarios", joinColumns = @JoinColumn(name = "medico_id"))
    private List<HorarioEntity> agendaDisponibilidad;
}