package org.udc.parcial.infrastructure.input.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.models.Paciente;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;
import org.udc.parcial.domain.ports.out.PacienteRepositoryPort;

import java.util.List;

@Controller
public class ViewController {

    private final MedicoRepositoryPort medicoRepositoryPort;
    private final PacienteRepositoryPort pacienteRepositoryPort;

    public ViewController(MedicoRepositoryPort medicoRepositoryPort, PacienteRepositoryPort pacienteRepositoryPort) {
        this.medicoRepositoryPort = medicoRepositoryPort;
        this.pacienteRepositoryPort = pacienteRepositoryPort;
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
        return "pacientes-lista"; // Apunta a pacientes-lista.html
    }

    @GetMapping("/citas")
    public String paginaCitas() {
        return "citas-agenda"; // Apunta a citas-agenda.html
    }
}