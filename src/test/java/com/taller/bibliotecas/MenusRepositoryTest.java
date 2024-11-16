package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Menus;
import com.taller.bibliotecas.entitys.Procesos;
import com.taller.bibliotecas.repository.MenusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

@SpringBootTest
public class MenusRepositoryTest {
    @Autowired
    private MenusRepository menusRepository;

    @Test
    public void saveMenuWithProceso(){
        Procesos procesos = Procesos.builder()
                .nombre("proceso-uno")
                .enlace("/proceso-uno")
                .estado(1L)
                .build();

        Procesos procesosDos = Procesos.builder()
                .nombre("proceso-dos")
                .enlace("/proceso-dos")
                .estado(1L)
                .build();

        Menus menus = Menus.builder()
                .nombre("menu-uno")
                .estado(1L)
                .procesosList(List.of(procesos,procesosDos))
                .build();

        menusRepository.save(menus);
    }
}
