package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Personas;
import com.taller.bibliotecas.repository.PersonasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
public class PersonasRepositoryTes {
    @Autowired
    private PersonasRepository personasRepository;

    @Test
    public void savePersona(){
        Personas personas = Personas.builder()
                .nombre("dante")
                .ap("coronel")
                .am("tolaba")
                .genero("masculino")
                .estado(1L)
                .tipo_per("director de biblioteca")
                .build();
        personasRepository.save(personas);
    }
}
