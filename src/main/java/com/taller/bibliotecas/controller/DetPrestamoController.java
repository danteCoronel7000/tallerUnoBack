package com.taller.bibliotecas.controller;

import com.taller.bibliotecas.entitys.DetPrestamo;
import com.taller.bibliotecas.entitys.DetPrestamoPK;
import com.taller.bibliotecas.entitys.Ejemplares;
import com.taller.bibliotecas.projections.classBased.DetPrestamoDTO;
import com.taller.bibliotecas.projections.classBased.EjemplaresDto;
import com.taller.bibliotecas.repository.DetPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/detprestamo")
public class DetPrestamoController {
    @Autowired
    DetPrestamoRepository detPrestamoRepository;

    @GetMapping(value = "all")
    public List<DetPrestamo> findAll(){
        return detPrestamoRepository.findAll();
    }

    @GetMapping(value = "allDto")
    public List<DetPrestamoDTO> allDetPrestamoDTo() {
        // Obtenemos los datos de la entidad dPrestamo (suponiendo que usas un repositorio)
        List<DetPrestamo> dPrestamos = detPrestamoRepository.findAll();

        // Mapeamos los datos de la entidad al DTO
        return dPrestamos.stream()
                .map(dPrestamo -> {
                    DetPrestamoDTO dto = new DetPrestamoDTO();
                    dto.setCodinv(dPrestamo.getEjemplar().getCodinv()); // Código del ejemplar
                    dto.setTitejemplar(dPrestamo.getEjemplar().getTexto().getTitulo()); // Título del texto
                    dto.setEstado(dPrestamo.getId_detPrestamoPK().getEstado()); // Estado de la llave compuesta
                    return dto;
                })
                .toList();
    }

    @GetMapping("/filtrarPorIdPrestamo/{id}")
    public ResponseEntity<List<DetPrestamoDTO>> getdetPrestamoByPreastamo(@PathVariable Long id) {
        // Buscar los detalles de préstamo por el id del préstamo
        List<DetPrestamo> detPrestamos = detPrestamoRepository.findByIdMprestamo(id);

        // Mapear los resultados a DetPrestamoDTO
        List<DetPrestamoDTO> detPrestamoDTOList = detPrestamos.stream()
                .map(d -> new DetPrestamoDTO(
                        d.getEjemplar().getCodinv(),  // Obtener el código del ejemplar
                        d.getEjemplar().getTexto().getTitulo(),  // Obtener el título del texto relacionado
                        d.getId_detPrestamoPK().getEstado()  // Obtener el estado del préstamo
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(detPrestamoDTOList);
    }
}
