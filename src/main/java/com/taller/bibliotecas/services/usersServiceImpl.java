package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.Personas;
import com.taller.bibliotecas.entitys.Usuarios;
import com.taller.bibliotecas.projections.classBased.UsuariosDTO;
import com.taller.bibliotecas.projections.interfaceBased.closed.UsersClosedView;
import com.taller.bibliotecas.projections.interfaceBased.closed.UsuariosAndPersonas;
import com.taller.bibliotecas.repository.UsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class usersServiceImpl implements UsersService{

    private final UsuariosRepository usuariosRepository;
    private final PersonasService personasService;
    private final PasswordEncoder passwordEncoder;

    public usersServiceImpl(UsuariosRepository usuariosRepository, PersonasService personasService, PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.personasService =personasService;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    @Override
    public List<UsersClosedView> findBy() {
        return usuariosRepository.findBy();
    }

    @Override
    public Optional<UsuariosAndPersonas> usuariosPorId(Long id) {
        return usuariosRepository.usuariosPorId(id);
    }

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        // Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Validar y asociar la persona
        if (usuario.getPersona() != null && usuario.getPersona().getId_persona() != null) {
            Long idPersona = usuario.getPersona().getId_persona();
            Personas persona = personasService.findById_persona(idPersona)
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + idPersona));
            usuario.setPersona(persona);
        } else {
            throw new RuntimeException("ID de persona no proporcionado");
        }

        // Guardar el usuario en la base de datos
        return usuariosRepository.save(usuario);
    }

    // Método para actualizar un usuario
    @Override
    public Usuarios actualizarUsuario(Usuarios usuario) {
        // Verifica si el usuario existe en la base de datos
        Optional<Usuarios> usuarioExistente = usuariosRepository.obtenerUsuarioPorId(usuario.getId_usuario());

        if (usuarioExistente.isPresent()) {
            // Si el usuario existe, copia los cambios y guárdalo
            Usuarios usuarioParaActualizar = usuarioExistente.get();
            usuarioParaActualizar.setPassword(usuario.getPassword());
            // Agrega todos los demás campos que deseas actualizar
            return usuariosRepository.save(usuarioParaActualizar);
        } else {
            throw new NoSuchElementException("Usuario no encontrado");
        }
    }

    @Override
    public List<UsuariosDTO> findUsuariosBy() {
        return usuariosRepository.findUsuariosBy();
    }

    @Override
    public Optional<Usuarios> findById_usuario(Long id_usuario) {
        return usuariosRepository.findById(id_usuario);
    }


}
