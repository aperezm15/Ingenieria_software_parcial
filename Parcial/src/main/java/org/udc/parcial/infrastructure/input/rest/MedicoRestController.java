package org.udc.parcial.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ports.in.RegistrarMedicoUseCase;
import org.udc.parcial.infrastructure.input.rest.dto.MedicoRequestDTO;
import org.udc.parcial.infrastructure.infrastructure.input.rest.dto.MedicoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoRestController {

    private final RegistrarMedicoUseCase registrarMedicoUseCase;

    // Inyección limpia por constructor
    public MedicoRestController(RegistrarMedicoUseCase registrarMedicoUseCase) {
        this.registrarMedicoUseCase = registrarMedicoUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> registrarMedico(@RequestBody MedicoRequestDTO request) {
        // 1. Convertir DTO de entrada al modelo de Dominio usando tu constructor con reglas de negocio
        Medico nuevoMedico = new Medico(
                request.getId(),
                request.getNombreCompleto(),
                request.getSpecialty()
        );

        // 2. Ejecutar el caso de uso que tienes definido en tus puertos de entrada
        Medico medicoRegistrado = registrarMedicoUseCase.ejecutarRegistro(nuevoMedico);

        // 3. Mapear el resultado de dominio al DTO de salida
        MedicoResponseDTO response = mapearADto(medicoRegistrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Helper privado para transformar el dominio al formato JSON de salida
    private MedicoResponseDTO mapearADto(Medico medico) {
        List<MedicoResponseDTO.HorarioDTO> horariosDto = medico.getAgendaDisponibilidad().stream()
                .map(h -> new MedicoResponseDTO.HorarioDTO(h.fechaHoraInicio(), h.fechaHoraFin(), h.disponible()))
                .collect(Collectors.toList());

        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNombreCompleto(),
                medico.getEspecialidad(),
                horariosDto
        );
    }
}