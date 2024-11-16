package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.Roles;
import com.taller.bibliotecas.projections.classBased.RolesDTO;
import com.taller.bibliotecas.projections.interfaceBased.closed.RolesClosedView;
import com.taller.bibliotecas.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Optional<Roles> findById_rol(Long id_rol) {
        return rolesRepository.findById(id_rol);
    }

    @Override
    public Roles save(Roles rol) {
        return rolesRepository.save(rol);
    }

    @Override
    public Roles deleteRoles(Long id_rol) {
        Roles rol = rolesRepository.findById(id_rol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id_rol));

        // Actualizar el estado del rol
        rol.setEstado(0L);

        // Guardar los cambios en la base de datos
        return rolesRepository.save(rol);
    }

    @Override
    public Roles habilitarRol(Long id_rol) {
        Roles rol = rolesRepository.findById(id_rol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id_rol));

        // Actualizar el estado del rol
        rol.setEstado(1L);

        // Guardar los cambios en la base de datos
        return rolesRepository.save(rol);
    }

    @Override
    public List<RolesClosedView> findRolesDto() {
        return rolesRepository.findBy();
    }
}
