package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Menus;
import com.taller.bibliotecas.entitys.Procesos;
import com.taller.bibliotecas.entitys.Roles;
import com.taller.bibliotecas.repository.MenusRepository;
import com.taller.bibliotecas.repository.RolesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RolesRepositoryTest {
    @Autowired
    private RolesRepository rolesRepository;
@Autowired
    private MenusRepository menusRepository;

    @Test
    public void saveRoles(){

        Roles roles = Roles.builder()
                .nombre("subdirector de Biblioteca")
                .estado(1L)
                .build();
        rolesRepository.save(roles);
    }

    @Test
    public void saveRolesWithMenus(){
        Menus menus = Menus.builder()
                .nombre("menu-dos")
                .estado(1L)
                .build();
        Menus menusDos = Menus.builder()
                .nombre("menu-tres")
                .estado(1L)
                .build();

        Roles roles = Roles.builder()
                .nombre("administrador de biblioteca")
                        .estado(1L)
                .menusList(List.of(menus, menusDos))
                                .build();

        rolesRepository.save(roles);
    }
}
