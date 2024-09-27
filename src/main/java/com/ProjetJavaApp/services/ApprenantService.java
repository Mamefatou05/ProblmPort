package com.ProjetJavaApp.services;

import com.ProjetJavaApp.data.entities.Apprenant;

import java.util.List;

public interface ApprenantService {
    List<Apprenant> findApprenants();

    List<Apprenant> findApprenantsByIds(List<Long> ids);
    Apprenant findApprenantById(Long id);

}
