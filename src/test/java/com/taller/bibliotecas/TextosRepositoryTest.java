package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.*;
import com.taller.bibliotecas.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextosRepositoryTest {
    @Autowired
    private TextosRepository textosRepository;
    @Autowired
    private AutoresRepository autoresRepository;
    @Autowired
    private AreasRepository areasRepository;
    @Autowired
    private EditorialesRepository editorialesRepository;
    @Autowired
    private TiposRepository tiposRepository;
    @Autowired
    private TipoTexRepository tipoTexRepository;

    @Test
    public void saveAreas() {
        Areas nuevaArea = Areas.builder()
                .nombre("Biologia")
                .estado(1L)
                .build();
        areasRepository.save(nuevaArea);
    }

    @Test
    public void saveTextos(){
        Textos textos = Textos.builder()
                .titulo("la jungla")
                .resumen("2].- Son 3 opciones: Activos= Áreas activas, Bajas=Áreas que fueron dados de baja, \n" +
                        "Todos=lista Áreas activas y los que fueron dados de baja. Esta opción se ejecuta al \n" +
                        "realizar los cambios")
                .isbm("usa")
                .edicion(232L)
                .fechapub("12-05-2'24")
                .build();
        textosRepository.save(textos);
    }

    @Test
    public void insertAutorTest() {
        Autores autor = Autores.builder()
                .nombre("Juan")
                .ap("Pérez")
                .am("García")
                .genero("Masculino")
                .estado(1L)
                .build();

        autoresRepository.save(autor);
    }

    @Test
    public void agregarNuevoTexto() {
        // Busca o crea las entidades relacionadas, si es necesario
        Areas area = areasRepository.findById(1L).orElseGet(() -> {
            Areas nuevaArea = Areas.builder()
                    .nombre("Nombre del Área")
                    .estado(1L)
                    .build();
            return areasRepository.save(nuevaArea);
        });

        Editoriales editorial = editorialesRepository.findById(1L).orElseGet(() -> {
            Editoriales nuevaEditorial = Editoriales.builder()
                    .nombre("Nombre de la Editorial")
                    .estado(1L)
                    .build();
            return editorialesRepository.save(nuevaEditorial);
        });

        // Crear la instancia de Textos con el constructor Builder
        Textos nuevoTexto = Textos.builder()
                .titulo("Nuevo Título")
                .resumen("Resumen del texto de prueba")
                .isbm("1234567890")
                .edicion(1L)
                .fechapub("2023-10-31")
                .area(area)
                .editorial(editorial)
                .build();

        // Guarda el nuevo texto en la base de datos
        textosRepository.save(nuevoTexto);

        System.out.println("Nuevo registro en Textos guardado: " + nuevoTexto);
    }



    @Test
    public void insertTipoTexTest() {
        // Busca o crea los objetos de `Textos` y `Tipos`
        Textos texto = textosRepository.findById(1L)
                .orElseGet(() -> textosRepository.save(
                        Textos.builder()
                                .titulo("Ejemplo de Título")
                                .resumen("Resumen de ejemplo")
                                .isbm("123456789")
                                .edicion(1L)
                                .fechapub("2023-01-01")
                                .build()
                ));

        // Asegúrate de que el texto tenga un ID
        if (texto.getId_texto() == null) {
            throw new IllegalStateException("El texto debe tener un ID asignado.");
        }

        Tipos tipo = tiposRepository.findById(1L)
                .orElseGet(() -> {
                    Tipos nuevoTipo = Tipos.builder()
                            .nombre("Tipo de Ejemplo")
                            .estado(1L)
                            .sw("S")
                            .build();
                    return tiposRepository.save(nuevoTipo);
                });

        // Asegúrate de que el tipo tenga un ID
        if (tipo.getId_tipo() == null) {
            throw new IllegalStateException("El tipo debe tener un ID asignado.");
        }

        // Define la clave primaria compuesta `TipoTextPK`
        TipoTextPK tipoTextPK = new TipoTextPK();
        tipoTextPK.setId_texto(texto.getId_texto());
        tipoTextPK.setId_tipo(tipo.getId_tipo());
        tipoTextPK.setDocum("nombre digital");

        // Crea el objeto `TipoTex` y lo guarda en la base de datos
        TipoTex tipoTex = TipoTex.builder()
                .idTipotex(tipoTextPK)
                .texto(texto)
                .tipo(tipo)
                .build();

        tipoTexRepository.save(tipoTex);
    }


}
