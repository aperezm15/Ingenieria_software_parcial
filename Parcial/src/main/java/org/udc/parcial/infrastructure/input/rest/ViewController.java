package org.udc.parcial.infrastructure.input.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.udc.parcial.domain.models.Cita;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.models.Paciente;
import org.udc.parcial.domain.ports.in.AgendarCitaUseCase;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;
import org.udc.parcial.domain.ports.out.PacienteRepositoryPort;

import java.util.List;

@Controller
public class ViewController {

    private final MedicoRepositoryPort medicoRepositoryPort;
    private final PacienteRepositoryPort pacienteRepositoryPort;
    private final AgendarCitaUseCase agendarCitaUseCase; // 👈 Añadido para el módulo de citas

    // Constructor unificado con todas las dependencias requeridas
    public ViewController(MedicoRepositoryPort medicoRepositoryPort,
                          PacienteRepositoryPort pacienteRepositoryPort,
                          AgendarCitaUseCase agendarCitaUseCase) {
        this.medicoRepositoryPort = medicoRepositoryPort;
        this.pacienteRepositoryPort = pacienteRepositoryPort;
        this.agendarCitaUseCase = agendarCitaUseCase;
    }

    @GetMapping("/medicos")
    public String paginaMedicos(Model model) {
        List<Medico> medicos = medicoRepositoryPort.listarTodos();
        model.addAttribute("listaMedicos", medicos);
        model.addAttribute("totalMedicos", medicos.size());
        return "medicos-lista";
    }

    @GetMapping("/pacientes")
    public String paginaPacientes(Model model) {
        List<Paciente> pacientes = pacienteRepositoryPort.listarTodos();
        model.addAttribute("listaPacientes", pacientes);
        model.addAttribute("totalPacientes", pacientes.size());
        return "pacientes-lista";
    }

    @GetMapping("/citas")
    public String paginaCitas(Model model) {
        // 1. Buscamos todas las citas reales guardadas en Supabase
        List<Cita> citas = agendarCitaUseCase.listarTodas();

        // 2. Enviamos las variables dinámicas a la plantilla de Thymeleaf
        model.addAttribute("listaCitas", citas);
        model.addAttribute("totalCitas", citas.size());

        return "citas-agenda"; // Apunta a citas-agenda.html
    }
}