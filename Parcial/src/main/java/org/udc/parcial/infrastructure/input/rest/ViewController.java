package org.udc.parcial.infrastructure.input.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/medicos")
    public String paginaMedicos() {
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