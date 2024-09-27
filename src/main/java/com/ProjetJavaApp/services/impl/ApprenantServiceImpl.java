package com.ProjetJavaApp.services.impl;

import com.ProjetJavaApp.data.entities.Apprenant;
import com.ProjetJavaApp.data.repositories.ApprenantRepository;
import com.ProjetJavaApp.exceptions.EntityNotFoundException;
import com.ProjetJavaApp.services.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenantServiceImpl implements ApprenantService {

    @Autowired
    private ApprenantRepository apprenantRepository;

    public List<Apprenant> findApprenantsByIds(List<Long> ids) {
        return apprenantRepository.findAllById(ids);
    }

    public Apprenant findApprenantById(Long id) {
        return apprenantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apprenant non trouvé: " + id));
    }

    @Override
    public List<Apprenant> findApprenants() {
        return apprenantRepository.findAll();
    }
}
