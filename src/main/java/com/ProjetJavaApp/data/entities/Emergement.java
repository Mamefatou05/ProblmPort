package com.ProjetJavaApp.data.entities;


import com.ProjetJavaApp.data.enums.PresenceEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emergements")
public class Emergement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateEntree;

    private LocalDateTime dateSortie;

    private PresenceEnum presence;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;
}
