package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.Menus;
import com.taller.bibliotecas.entitys.Procesos;
import com.taller.bibliotecas.entitys.Roles;
import com.taller.bibliotecas.entitys.Usuarios;
import com.taller.bibliotecas.projections.classBased.MenusAsigNoAsig;
import com.taller.bibliotecas.projections.classBased.ProcesosAsigNoAsig;
import com.taller.bibliotecas.projections.classBased.RolesAsigNoAsig;
import com.taller.bibliotecas.repository.MenusRepository;
import com.taller.bibliotecas.repository.ProcesosRepository;
import com.taller.bibliotecas.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenusServiceImpl implements MenuService{
    @Autowired
    MenusRepository menusRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    ProcesosRepository procesosRepository;

    @Override
    public Optional<Menus> findById_menu(Long id_menu) {
        return menusRepository.findById(id_menu);
    }

    @Override
    public Menus save(Menus menu) {
        return menusRepository.save(menu);
    }

    @Override
    public Menus deleteMenu(Long id_menu) {
        Menus menu = menusRepository.findById(id_menu)
                .orElseThrow(() -> new RuntimeException("Menu no encontrado con id: " + id_menu));

        // Actualizar el estado del menu
        menu.setEstado(0L);

        // Guardar los cambios en la base de datos
        return menusRepository.save(menu);
    }

    @Override
    public Menus habilitarMenu(Long id_menu) {
        Menus menu = menusRepository.findById(id_menu)
                .orElseThrow(() -> new RuntimeException("Menu no encontrado con id: " + id_menu));

        // Actualizar el estado del menu
        menu.setEstado(1L);

        // Guardar los cambios en la base de datos
        return menusRepository.save(menu);
    }

    @Override
    public List<MenusAsigNoAsig> findAllAsigNoAsig() {
        // Obtener todas las entidades Roles y Usuarios
        List<Menus> roles = menusRepository.findAll();
        List<Roles> usuarios = rolesRepository.findAll();

        List<MenusAsigNoAsig> rolesAsigNoAsigList = new ArrayList<>();

        // Procesar cada rol para determinar su asignación
        for (Menus rol : roles) {
            MenusAsigNoAsig rolDto = new MenusAsigNoAsig();
            rolDto.setId_menu(rol.getId_menu());
            rolDto.setNombre(rol.getNombre());
            rolDto.setEstado(rol.getEstado());
            rolDto.setAsig(0L); // Por defecto, no asignado

            // Verificar si el rol está asignado a algún usuario
            for (Roles usuario : usuarios) {
                if (usuario.getMenusList().stream().anyMatch(r -> r.getId_menu().equals(rol.getId_menu()))) {
                    rolDto.setAsig(usuario.getId_rol()); // Asignar el ID del usuario
                    break;
                }
            }

            rolesAsigNoAsigList.add(rolDto);
        }

        return rolesAsigNoAsigList;
    }

    @Override
    public List<ProcesosAsigNoAsig> findAllProcesosAsigNoAsig() {
        // Obtener todas las entidades Procesos y Menus
        List<Procesos> procesos = procesosRepository.findAll();
        List<Menus> menus = menusRepository.findAll();

        List<ProcesosAsigNoAsig> procesosAsigNoAsigList = new ArrayList<>();

        // Procesar cada proceso para determinar su asignación
        for (Procesos proceso : procesos) {
            ProcesosAsigNoAsig procesoDto = new ProcesosAsigNoAsig();
            procesoDto.setId_proceso(proceso.getId_proceso());
            procesoDto.setNombre(proceso.getNombre());
            procesoDto.setEstado(proceso.getEstado());
            procesoDto.setAsig(0L); // Por defecto, no asignado

            // Verificar si el proceso está asignado a algún menu
            for (Menus menu : menus) {
                if (menu.getProcesosList().stream().anyMatch(p -> p.getId_proceso().equals(proceso.getId_proceso()))) {
                    procesoDto.setAsig(menu.getId_menu()); // Asignar el ID del menu
                    break;
                }
            }

            procesosAsigNoAsigList.add(procesoDto);
        }

        return procesosAsigNoAsigList;
    }


}
