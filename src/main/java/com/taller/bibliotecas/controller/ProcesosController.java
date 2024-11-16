package com.taller.bibliotecas.controller;


import com.taller.bibliotecas.entitys.Menus;
import com.taller.bibliotecas.entitys.Procesos;
import com.taller.bibliotecas.repository.ProcesosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/procesos")
public class ProcesosController {
    @Autowired
    ProcesosRepository procesosRepository;


    @GetMapping(value = "all")
    public List<Procesos> findAll(){
        return procesosRepository.findAll();
    }

}
