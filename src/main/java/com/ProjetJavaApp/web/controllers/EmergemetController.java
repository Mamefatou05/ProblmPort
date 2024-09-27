package com.ProjetJavaApp.web.controllers;

import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.web.dto.requests.emargements.EmargementDTO;
import com.ProjetJavaApp.web.dto.requests.emargements.EmargementGroupeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmergemetController {
    ResponseEntity<?> enregistrerEmargementIndividuel(EmargementDTO dto);
    ResponseEntity<?> enregistrerEmargementGroupe(EmargementGroupeDTO dto);
    ResponseEntity<List<Emergement>> listerEmargements(String mois, String referentiel, String date);
}
