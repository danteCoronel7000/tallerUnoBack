package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.MPrestamo;
import com.taller.bibliotecas.projections.classBased.CrearPrestamoDTO;

public interface MPrestamoService {
    MPrestamo createPrestamo(CrearPrestamoDTO dto);
}
