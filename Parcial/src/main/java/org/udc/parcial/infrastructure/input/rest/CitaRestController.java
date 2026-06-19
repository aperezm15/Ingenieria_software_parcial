package org.udc.parcial.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udc.parcial.domain.models.Cita;
import org.udc.parcial.domain.ports.in.AgendarCitaUseCase;
import org.udc.parcial.infrastructure.input.rest.dto.CitaRequestDTO;
import org.udc.parcial.infrastructure.input.rest.dto.CitaResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaRestController {

    private final AgendarCitaUseCase agendarCitaUseCase;

    // Inyección explícita por constructor para cumplir el desacoplamiento
    public CitaRestController(AgendarCitaUseCase agendarCitaUseCase) {
        this.agendarCitaUseCase = agendarCitaUseCase;
    }

    @PostMapping
    public ResponseEntity<CitaResponseDTO> agendarCita(@RequestBody CitaRequestDTO request) {
        // 1. Convertir el DTO entrante a un objeto de Dominio Puro (valida consistencias de negocio primarias)
        Cita nuevaCita = new Cita(
                request.getCodigoCita(),
                request.getPacienteId(),
                request.getMedicoId(),
                request.getFechaHoraInicio(),
                request.getFechaHoraFin(),
                "AGENDADA"
        );

        // 2. Invocar el caso de uso que procesa la orquestación y las reglas de concurrencia
        Cita citaAgendada = agendarCitaUseCase.ejecutarAgendamiento(nuevaCita);

        // 3. Retornar el DTO de salida con el estado de éxito correspondiente
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearADto(citaAgendada));
    }

    @GetMapping("/{codigoCita}")
    public ResponseEntity<CitaResponseDTO> obtenerCitaPorCodigo(@PathVariable String codigoCita) {
        return agendarCitaUseCase.buscarPorCodigo(codigoCita)
                .map(cita -> ResponseEntity.ok(mapearADto(cita)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<CitaResponseDTO>> listarTodasLasCitas() {
        List<Cita> citas = agendarCitaUseCase.listarTodas();
        List<CitaResponseDTO> respuesta = citas.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    // Helper privado para transformar de Dominio a DTO de salida HTTP
    private CitaResponseDTO mapearADto(Cita cita) {
        return new CitaResponseDTO(
                cita.getCodigoCita(),
                cita.getPacienteId(),
                cita.getMedicoId(),
                cita.getFechaHoraInicio(),
                cita.getFechaHoraFin(),
                cita.getEstado()
        );
    }
}