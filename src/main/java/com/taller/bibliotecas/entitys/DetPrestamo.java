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
@Table(name = "detprestamo")
public class DetPrestamo {
    @EmbeddedId
    private DetPrestamoPK id_detPrestamoPK;

    @ManyToOne
    @MapsId("id_ejemplar")
    @JoinColumn(name = "id_ejemplar")
    Ejemplares ejemplar;

    @ManyToOne
    @MapsId("id_mprestamo")
    @JoinColumn(name = "id_mprestamo")
    MPrestamo mprestamo;
}
