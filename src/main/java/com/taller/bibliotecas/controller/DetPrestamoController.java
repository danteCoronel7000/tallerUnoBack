package com.taller.bibliotecas.controller;

import com.taller.bibliotecas.entitys.DetPrestamo;
import com.taller.bibliotecas.entitys.DetPrestamoPK;
import com.taller.bibliotecas.repository.DetPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/detprestamo")
public class DetPrestamoController {
    @Autowired
    DetPrestamoRepository detPrestamoRepository;

    @GetMapping(value = "all")
    public List<DetPrestamo> findAll(){
        return detPrestamoRepository.findAll();
    }
}
