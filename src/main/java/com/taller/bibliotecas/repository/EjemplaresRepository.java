package com.taller.bibliotecas.repository;

import com.taller.bibliotecas.entitys.Ejemplares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjemplaresRepository extends JpaRepository<Ejemplares,Long> {


    @Query(value = "SELECT * FROM ejemplares WHERE id_texto = :idTexto", nativeQuery = true)
    List<Ejemplares> findEjemplaresByTextoId(@Param("idTexto") Long idTexto);
}
