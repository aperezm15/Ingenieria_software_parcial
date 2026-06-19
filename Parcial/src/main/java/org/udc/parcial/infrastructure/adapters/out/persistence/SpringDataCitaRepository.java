package org.udc.parcial.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SpringDataCitaRepository extends JpaRepository<CitaEntity, String> {

    // Query lógica para detectar solapamiento de tiempo
    @Query("SELECT COUNT(c) > 0 FROM CitaEntity c WHERE c.medicoId = :medicoId " +
            "AND c.estado = 'AGENDADA' " +
            "AND (:inicio < c.fechaHoraFin AND :fin > c.fechaHoraInicio)")
    boolean existsOverlappingCita(@Param("medicoId") String medicoId,
                                  @Param("inicio") LocalDateTime inicio,
                                  @Param("fin") LocalDateTime fin);
}