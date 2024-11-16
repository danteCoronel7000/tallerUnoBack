package com.taller.bibliotecas.repository;

import com.taller.bibliotecas.entitys.DetPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetPrestamoRepository extends JpaRepository<DetPrestamo, Long> {
}
