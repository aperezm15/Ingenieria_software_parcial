package org.udc.parcial.infrastructure.input.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ¡No olvides esta importación!
import org.springframework.web.bind.annotation.GetMapping;
import org.udc.parcial.domain.models.Medico;
import org.udc.parcial.domain.ports.out.MedicoRepositoryPort;

import java.util.List;

@Controller
public class ViewController {

    // Inyectamos el puerto del repositorio para poder consultar Supabase
    private final MedicoRepositoryPort medicoRepositoryPort;

    public ViewController(MedicoRepositoryPort medicoRepositoryPort) {
        this.medicoRepositoryPort = medicoRepositoryPort;
    }

    @GetMapping("/medicos")
    public String paginaMedicos(Model model) {
        // 1. Buscamos los médicos reales en la Base de Datos
        List<Medico> medicos = medicoRepositoryPort.listarTodos();

        // 2. Se los pasamos a la vista con los mismos nombres que pusimos en el HTML
        model.addAttribute("listaMedicos", medicos);
        model.addAttribute("totalMedicos", medicos.size()); // Esto calcula el número para el contador

        return "medicos-lista"; // Apunta a medicos-lista.html
    }

    @GetMapping("/pacientes")
    public String paginaPacientes() {
        return "pacientes-lista"; // Apunta a pacientes-lista.html
    }

    @GetMapping("/citas")
    public String paginaCitas() {
        return "citas-agenda"; // Apunta a citas-agenda.html
    }
}