package com.taller.bibliotecas.controller;

import com.taller.bibliotecas.entitys.Autores;
import com.taller.bibliotecas.entitys.MPrestamo;
import com.taller.bibliotecas.repository.MPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mprestamo")
public class MPrestamoController {
    @Autowired
    MPrestamoRepository mPrestamoRepository;

    @GetMapping(value = "all")
    public List<MPrestamo> findAll(){
        return mPrestamoRepository.findAll();
    }
}
