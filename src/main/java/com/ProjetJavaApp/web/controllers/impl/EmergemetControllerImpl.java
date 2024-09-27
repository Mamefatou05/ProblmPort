package com.ProjetJavaApp.web.controllers.impl;
//import io.swagger.annotations.Api;
import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.web.controllers.EmergemetController;
import org.springframework.http.HttpStatus; // Assurez-vous que cet import est présent
import com.ProjetJavaApp.services.EmergementService;
import com.ProjetJavaApp.web.dto.requests.emargements.EmargementDTO;
import com.ProjetJavaApp.web.dto.requests.emargements.EmargementGroupeDTO;
import com.ProjetJavaApp.web.dto.mappers.EmargementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "Gestion des émargements")
@RestController
@RequestMapping("/emargements")
public class EmergemetControllerImpl implements EmergemetController {
    @Autowired
    private EmergementService emargementService;

    @Autowired
    private EmargementMapper emargementMapper;

    // Enregistrer un émargement individuel
    @PostMapping("/individuel")
    public ResponseEntity<?> enregistrerEmargementIndividuel(@RequestBody EmargementDTO dto) {
        emargementService.enregistrerEmargement(dto.getApprenantId(), dto.typeEmargement);
        return ResponseEntity.ok("Émargement individuel enregistré avec succès");
    }

    // Enregistrer un émargement de groupe
    @PostMapping("/groupe")
    public ResponseEntity<?> enregistrerEmargementGroupe(@RequestBody EmargementGroupeDTO dto) {
        try {
            // Passez simplement les IDs des apprenants et le type d'émargement au service
            emargementService.enregistrerEmargementGroupe(dto.getApprenantIds(), dto.getTypeEmargement());
            return ResponseEntity.ok("Émargement de groupe enregistré avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement des émargements : " + e.getMessage());
        }
    }

    @GetMapping("/All")
    public ResponseEntity<List<Emergement>> listerEmargements(
            @RequestParam(required = false) String mois,
            @RequestParam(required = false) String referentiel,
            @RequestParam(required = false) String date // Gardez ceci comme String
    ) {

        List<Emergement> Emergement = emargementService.listerEmargements(mois, date);
        return ResponseEntity.ok(Emergement);
    }


//    // Modifier un émargement
//    @PutMapping("/{id}")
//    public ResponseEntity<?> modifierEmargement(@PathVariable Long id, @RequestBody EmargementDTO dto) {
//        Emergement updatedEmargement = emargementMapper.toEntity(dto);
//        emargementService.modifierEmargement(id, updatedEmargement);
//        return ResponseEntity.ok("Émargement modifié avec succès");
//    }
}
