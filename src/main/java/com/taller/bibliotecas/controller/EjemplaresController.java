package com.taller.bibliotecas.controller;

import com.taller.bibliotecas.entitys.Autores;
import com.taller.bibliotecas.entitys.Ejemplares;
import com.taller.bibliotecas.projections.classBased.EjemplaresDto;
import com.taller.bibliotecas.repository.EjemplaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filtrarPorIdTexto/{id}")
    public ResponseEntity<List<EjemplaresDto>> getEjemplaresByTexto(@PathVariable Long id) {
        List<Ejemplares> ejemplares = ejemplaresRepository.findEjemplaresByTextoId(id);
        // Mapear a EjemplaresDto
        List<EjemplaresDto> ejemplaresDtoList = ejemplares.stream()
                .map(e -> new EjemplaresDto(
                        e.getId_ejemplar(),
                        e.getDisponible(),
                        e.getEstado()
                )).toList();
        return ResponseEntity.ok(ejemplaresDtoList);
    }
}
