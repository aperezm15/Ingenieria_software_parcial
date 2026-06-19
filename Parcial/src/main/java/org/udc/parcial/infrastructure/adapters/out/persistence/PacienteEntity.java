package org.udc.parcial.infrastructure.adapters.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntity {

    @Id
    private String id; // Se usará la cédula/documento único como Primary Key en Supabase

    private String nombreCompleto;
    private String correo;
    private String telefono;
}