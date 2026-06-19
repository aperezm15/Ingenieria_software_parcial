package org.udc.parcial.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udc.parcial.domain.models.Paciente;
import org.udc.parcial.domain.ports.in.RegistrarPacienteUseCase;
import org.udc.parcial.infrastructure.input.rest.dto.PacienteRequestDTO;
import org.udc.parcial.infrastructure.input.rest.dto.PacienteResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteRestController {

    private final RegistrarPacienteUseCase registrarPacienteUseCase;

    // Inyección explícita por constructor
    public PacienteRestController(RegistrarPacienteUseCase registrarPacienteUseCase) {
        this.registrarPacienteUseCase = registrarPacienteUseCase;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> registrarPaciente(@RequestBody PacienteRequestDTO request) {
        // 1. Mapear de DTO de entrada al objeto de negocio (gatilla las validaciones nativas)
        Paciente nuevoPaciente = new Paciente(
                request.getId(),
                request.getNombreCompleto(),
                request.getCorreo(),
                request.getTelefono()
        );

        // 2. Invocar el caso de uso
        Paciente pacienteRegistrado = registrarPacienteUseCase.ejecutarRegistro(nuevoPaciente);

        // 3. Devolver la respuesta en el DTO de salida
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearADto(pacienteRegistrado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPacientePorId(@PathVariable String id) {
        return registrarPacienteUseCase.buscarPorId(id)
                .map(paciente -> ResponseEntity.ok(mapearADto(paciente)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodosLosPacientes() {
        List<Paciente> pacientes = registrarPacienteUseCase.listarTodos();
        List<PacienteResponseDTO> respuesta = pacientes.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    // Método helper privado de conversión Dominio -> DTO
    private PacienteResponseDTO mapearADto(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNombreCompleto(),
                paciente.getCorreo(),
                paciente.getTelefono()
        );
    }
}