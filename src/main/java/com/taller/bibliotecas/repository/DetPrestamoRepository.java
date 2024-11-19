package com.taller.bibliotecas.repository;

import com.taller.bibliotecas.entitys.DetPrestamo;
import com.taller.bibliotecas.entitys.Ejemplares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetPrestamoRepository extends JpaRepository<DetPrestamo, Long> {

    @Query(value = "SELECT * FROM detprestamo WHERE id_mprestamo = :id", nativeQuery = true)
    List<DetPrestamo> findByIdMprestamo(@Param("id") Long id);
}
