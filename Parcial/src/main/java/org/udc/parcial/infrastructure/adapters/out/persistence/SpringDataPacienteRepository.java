package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPacienteRepository extends JpaRepository<PacienteEntity, String> {
    // Spring Data hereda las consultas nativas (save, findById, findAll) hacia PostgreSQL
}