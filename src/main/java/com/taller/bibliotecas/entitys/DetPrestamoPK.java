package com.taller.bibliotecas.entitys;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class DetPrestamoPK implements Serializable {
    protected Long id_ejemplar;
    protected Long id_mprestamo;
    protected String docum;//nombre que se le dara al documento
}
