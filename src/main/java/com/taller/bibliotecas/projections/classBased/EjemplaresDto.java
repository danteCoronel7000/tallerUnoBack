package com.taller.bibliotecas.projections.classBased;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EjemplaresDto {
    private Long id_ejemplar;

    private Long disponible;

    private Long estado;
}
