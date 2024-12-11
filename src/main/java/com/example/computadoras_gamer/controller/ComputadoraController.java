/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.computadoras_gamer.controller;

import com.example.computadoras_gamer.model.Computadora;
import com.example.computadoras_gamer.repository.ComputadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computadoras")
public class ComputadoraController {

    @Autowired
    private ComputadoraRepository computadoraRepository;

    // Método para listar computadoras
    @GetMapping
    public String listarComputadoras(Model model) {
        model.addAttribute("computadoras", computadoraRepository.findAll());
        return "index";  // Regresa a la vista 'index' que debería mostrar la lista de computadoras
    }

    // Método para mostrar el formulario de agregar nueva computadora
    @GetMapping("/nueva")
    public String mostrarFormularioAgregar(Model model) {
    model.addAttribute("computadora", new Computadora());  // Crea un objeto vacío de Computadora para el formulario
    return "agregar";  // Regresa la vista 'agregar.html'
}

    // Método para guardar la computadora nueva
    @PostMapping
    public String guardarComputadora(@ModelAttribute Computadora computadora) {
        try {
            computadoraRepository.save(computadora);  // Guardamos la computadora en la base de datos
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el error en la consola
            return "error";  // Redirige a una página de error (asegúrate de crear una vista llamada 'error.html')
        }
        return "redirect:/computadoras";  // Redirige al listado de computadoras después de guardar
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
    // Se busca la computadora por ID, si no se encuentra se lanza una excepción
    Computadora computadora = computadoraRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
    
    // Se añade la computadora al modelo para que pueda ser usada en la vista
    model.addAttribute("computadora", computadora);
    return "editar";  // Regresa la vista 'editar.html'
}

    // Método para actualizar la computadora
    @PostMapping("/{id}")
    public String actualizarComputadora(@PathVariable Long id, @ModelAttribute Computadora computadora) {
        computadora.setId(id);  // Establecemos el ID antes de guardar
        computadoraRepository.save(computadora);  // Guardamos la computadora actualizada
        return "redirect:/computadoras";  // Redirige al listado de computadoras
    }

    // Método para eliminar la computadora
    @GetMapping("/eliminar/{id}")
    public String eliminarComputadora(@PathVariable Long id) {
        computadoraRepository.deleteById(id);  // Eliminamos la computadora con el ID proporcionado
        return "redirect:/computadoras";  // Redirige al listado de computadoras
    }
}