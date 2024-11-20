package com.taller.bibliotecas.services;

import com.taller.bibliotecas.entitys.*;
import com.taller.bibliotecas.projections.classBased.CrearPrestamoDTO;
import com.taller.bibliotecas.projections.classBased.EjemplarDtoPPrestamos;
import com.taller.bibliotecas.projections.classBased.EjemplaresDto;
import com.taller.bibliotecas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MprestamoServiceImpl implements MPrestamoService{
    @Autowired
    private MPrestamoRepository mPrestamoRepository;

    @Autowired
    private DatosRepository datosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private DetPrestamoRepository detPrestamoRepository;

    @Autowired
    private EjemplaresRepository ejemplaresRepository;

    @Override
    public MPrestamo createPrestamo(CrearPrestamoDTO prestamoDTO) {
        // 1. Obtener el "Datos" y "Usuarios" usando sus respectivos IDs
        Datos datos = datosRepository.findById(prestamoDTO.getId_dato())
                .orElseThrow(() -> new RuntimeException("Datos no encontrados"));
        Usuarios usuario = usuariosRepository.findById(prestamoDTO.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Crear la entidad "MPrestamo"
        MPrestamo prestamo = new MPrestamo();
        prestamo.setFecha(prestamoDTO.getFecha());
        prestamo.setFechaini(prestamoDTO.getFechaini());
        prestamo.setFechafin(prestamoDTO.getFechafin());
        prestamo.setTipopres(prestamoDTO.getTipopres());
        prestamo.setEstado(prestamoDTO.getEstado());
        prestamo.setRealiza(datos);
        prestamo.setPresta(usuario);

        // 3. Guardar el "MPrestamo"
        MPrestamo savedPrestamo = mPrestamoRepository.save(prestamo);

        // 4. Relacionar los ejemplares con el "MPrestamo"
        for (EjemplarDtoPPrestamos ejemplarDTO : prestamoDTO.getListEjemplares()) {
            Ejemplares ejemplar = ejemplaresRepository.findById(ejemplarDTO.getId_ejemplar())
                    .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));

            // Crear la relación entre "MPrestamo" y "Ejemplares" (detaller de préstamo)
            DetPrestamo detPrestamo = new DetPrestamo();

            // Crear la clave compuesta utilizando el ID del préstamo y del ejemplar
            DetPrestamoPK detPrestamoPK = new DetPrestamoPK();
            detPrestamoPK.setId_mprestamo(savedPrestamo.getId_mprestamo());
            detPrestamoPK.setId_ejemplar(ejemplar.getId_ejemplar());
            detPrestamoPK.setEstado(1L); // Asumiendo que el estado es parte de la relación

            detPrestamo.setId_detPrestamoPK(detPrestamoPK);
            detPrestamo.setMprestamo(savedPrestamo);
            detPrestamo.setEjemplar(ejemplar);

            // Guardar la relación de "DetPrestamo" (muchos a muchos)
            detPrestamoRepository.save(detPrestamo);
        }

        return savedPrestamo;
    }
}
