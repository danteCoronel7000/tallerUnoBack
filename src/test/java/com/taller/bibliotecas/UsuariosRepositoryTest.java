package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Roles;
import com.taller.bibliotecas.entitys.Usuarios;
import com.taller.bibliotecas.repository.PersonasRepository;
import com.taller.bibliotecas.repository.RolesRepository;
import com.taller.bibliotecas.repository.UsuariosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UsuariosRepositoryTest {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private PersonasRepository personasRepository;
    @Autowired
    private RolesRepository rolesRepository;


    @Test
    public void saveUsuarioWithRoles(){
        Roles roles = Roles.builder()
                .nombre("jefe de procesos tecnicos")
                .estado(1L)
                .build();
        Roles rolesDos = Roles.builder()
                .nombre("jefe de servicios al publico")
                .estado(1L)
                .build();

        Usuarios usuarios = Usuarios.builder()
                .estado(1L)
                .password("123")
                .username("dante")
                .rolesList(List.of(roles, rolesDos))
                .build();
        usuariosRepository.save(usuarios);
    }

    @Test
    public void findUsuarios(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        System.out.println(usuarios);
    }
}

