package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.Menus;
import com.taller.bibliotecas.repository.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenusServiceImpl implements MenuService{
    @Autowired
    MenusRepository menusRepository;

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

}
