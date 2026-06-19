package org.udc.parcial.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ports.in.RegistrarMedicoUseCase;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;
import org.udc.parcial.infrastructure.input.rest.dto.MedicoRequestDTO;
import org.udc.parcial.infrastructure.infrastructure.input.rest.dto.MedicoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoRestController {

    private final RegistrarMedicoUseCase registrarMedicoUseCase;
    private final MedicoRepositoryPort medicoRepositoryPort;

    public MedicoRestController(RegistrarMedicoUseCase registrarMedicoUseCase, MedicoRepositoryPort medicoRepositoryPort) {
        this.registrarMedicoUseCase = registrarMedicoUseCase;
        this.medicoRepositoryPort = medicoRepositoryPort;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> registrarMedico(@RequestBody MedicoRequestDTO request) {
        Medico nuevoMedico = new Medico(request.getId(), request.getNombreCompleto(), request.getSpecialty());
        Medico medicoRegistrado = registrarMedicoUseCase.ejecutarRegistro(nuevoMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearADto(medicoRegistrado));
    }

    // Endpoint 1: Obtener un médico por su Cédula
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> obtenerMedicoPorId(@PathVariable String id) {
        return medicoRepositoryPort.buscarPorId(id)
                .map(medico -> ResponseEntity.ok(mapearADto(medico)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint 2: Listar todos los médicos de la base de datos
    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarTodosLosMedicos() {
        List<Medico> medicos = medicoRepositoryPort.listarTodos();
        List<MedicoResponseDTO> respuesta = medicos.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    // Helper privado de mapeo
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