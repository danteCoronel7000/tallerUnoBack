package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Procesos;
import com.taller.bibliotecas.repository.ProcesosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProcesosRepositoryTest {
    @Autowired
    private ProcesosRepository procesosRepository;

    @Test
    public void saveProceso(){
        Procesos procesos = Procesos.builder()
                .nombre("cargar")
                .enlace("/tables")
                .estado(1L)
                .build();
    }
}
