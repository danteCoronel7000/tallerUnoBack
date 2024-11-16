package com.taller.bibliotecas.controller;

import com.taller.bibliotecas.entitys.Autores;
import com.taller.bibliotecas.entitys.Ejemplares;
import com.taller.bibliotecas.repository.EjemplaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplaresController {

    @Autowired
    EjemplaresRepository ejemplaresRepository;

    @GetMapping(value = "all")
    public List<Ejemplares> findAll(){
        return ejemplaresRepository.findAll();
    }
}
