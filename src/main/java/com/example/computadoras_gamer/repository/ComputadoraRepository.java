/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.computadoras_gamer.repository;

import com.example.computadoras_gamer.model.Computadora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputadoraRepository extends JpaRepository<Computadora, Long> {
}