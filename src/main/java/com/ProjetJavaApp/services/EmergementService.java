package com.ProjetJavaApp.services;

import com.ProjetJavaApp.data.enums.TypeEmargEnum;
import com.ProjetJavaApp.data.entities.Emergement;

import java.util.List;

public interface EmergementService {
    void enregistrerEmargement(Long apprenantId, TypeEmargEnum typeEmargement);
    public void enregistrerEmargementGroupe(List<Long> apprenantIds, TypeEmargEnum typeEmargement);
    List<Emergement> listerEmargements(String mois, String date);
    void modifierEmargement(Long id, Emergement updatedEmargement);
    void marquerAbsencesJournee();
}