package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMedicoRepository extends JpaRepository<MedicoEntity, String> {
    // Spring Data se encarga de implementar esto internamente hacia PostgreSQL
}