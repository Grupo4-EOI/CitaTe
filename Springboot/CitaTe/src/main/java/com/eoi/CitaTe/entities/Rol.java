package com.eoi.CitaTe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rol", nullable = false)
    private Long id;

    private String nombreRol;

    @OneToOne(mappedBy = "rol", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Permiso> permisos;

}