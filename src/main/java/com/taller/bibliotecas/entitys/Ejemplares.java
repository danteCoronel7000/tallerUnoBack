package com.taller.bibliotecas.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ejemplares")
public class Ejemplares {
    @Id
    @SequenceGenerator(
            name = "ejemplares_secuence",
            sequenceName = "ejemplares_secuence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ejemplares_secuence"
    )
    @Column(name = "id_ejemplar")
    Long id_ejemplar;
    @Column(nullable = false)
    Long disponible;
    @Column(name = "estado")
    Long estado;
}
