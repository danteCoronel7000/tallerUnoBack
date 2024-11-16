package com.taller.bibliotecas.repository;

import com.taller.bibliotecas.entitys.Ejemplares;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplaresRepository extends JpaRepository<Ejemplares,Long> {
}
