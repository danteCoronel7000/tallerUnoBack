package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.Menus;

import java.util.Optional;

public interface MenuService {
    Optional<Menus> findById_menu(Long id_menu);

    Menus save(Menus menu);

    Menus deleteMenu(Long id_menu);

    Menus habilitarMenu(Long id_menu);

}
