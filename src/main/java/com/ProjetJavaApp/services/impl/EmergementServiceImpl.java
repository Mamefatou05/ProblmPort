package com.ProjetJavaApp.services.impl;

import com.ProjetJavaApp.data.entities.Apprenant;
import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.data.enums.TypeEmargEnum;
import com.ProjetJavaApp.data.repositories.EmergementRepository;
import com.ProjetJavaApp.services.ApprenantService;
import com.ProjetJavaApp.services.EmergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergementServiceImpl implements EmergementService {

    @Autowired
    private EmergementRepository emargementRepository;
    @Autowired
    private ApprenantService apprenantService;

    private Emergement createEmergement(Long apprenantId, TypeEmargEnum typeEmargement) {
        // Récupérer l'apprenant depuis la base de données
        Apprenant apprenant = apprenantService.findApprenantById(apprenantId);

        // Créer et configurer l'émargement
    Emergement emargement = new Emergement();
        emargement.setApprenant(apprenant);

        LocalDateTime now = LocalDateTime.now(); // Utilisez LocalDateTime pour inclure l'heure

        if (typeEmargement == TypeEmargEnum.ENTRER) {
            emargement.setDateEntree(now); // Assurez-vous que dateEntree accepte LocalDateTime
        } else if (typeEmargement == TypeEmargEnum.SORTIE) {
            emargement.setDateSortie(now); // Assurez-vous que dateSortie accepte LocalDateTime
        }

  return emargement;
    }

    @Override
    public void enregistrerEmargement(Long apprenantId, TypeEmargEnum typeEmargement) {
        Emergement emargement = createEmergement(apprenantId, typeEmargement);

        // Enregistrer l'émargement dans la base de données
        emargementRepository.save(emargement);
    }

    @Override
    @Transactional
    public void enregistrerEmargementGroupe(List<Long> apprenantIds, TypeEmargEnum typeEmargement) {
        List<Emergement> emargements = apprenantIds.stream()
                .map(apprenantId -> createEmergement(apprenantId, typeEmargement))
                .collect(Collectors.toList());

        // Enregistrer les émargements dans la base de données
        emargementRepository.saveAll(emargements);
    }


    @Override
    public List<Emergement> listerEmargements(String mois, String date) {
        if (date != null) {
            // Parse la date au format "yyyy-MM-dd"
            LocalDate localDate = LocalDate.parse(date);
            return emargementRepository.findAllByDate(localDate);
        } else if (mois != null) {
            // Parse le mois au format "yyyy-MM"
            YearMonth yearMonth = YearMonth.parse(mois);
            int year = yearMonth.getYear();
            int month = yearMonth.getMonthValue();
            return emargementRepository.findAllByMonthAndYear(month, year);
        } else {
            // Récupérer tous les enregistrements si aucun filtre n'est fourni
            return emargementRepository.findAll();
        }
    }


    @Override
    public void modifierEmargement(Long id, Emergement updatedEmargement) {
        Emergement existingEmargement = emargementRepository.findById(id).orElseThrow(() -> new RuntimeException("Emargement not found"));
        existingEmargement.setDateEntree(updatedEmargement.getDateEntree());
        existingEmargement.setDateSortie(updatedEmargement.getDateSortie());
        existingEmargement.setApprenant(updatedEmargement.getApprenant());
        emargementRepository.save(existingEmargement);
    }

    @Transactional
    public void marquerAbsencesJournee() {
        List<Apprenant> apprenants = apprenantService.findApprenants();

        // Normaliser la date pour comparer uniquement la date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date day = calendar.getTime();

        for (Apprenant apprenant : apprenants) {
            long nombreEmargements = emargementRepository.countByApprenantAndDateEntree(apprenant, day);

            if (nombreEmargements <= 1) {
//                apprenant.setStatut(StatutApprenantEnum.ABSENT);
//                apprenantservice.save(apprenant);
            }
        }
    }

}
