package com.ProjetJavaApp.services.impl;


import com.ProjetJavaApp.data.repositories.ModuleRepository;
import com.ProjetJavaApp.exceptions.ServiceException;
import com.ProjetJavaApp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProjetJavaApp.data.entities.Module;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository modulesRepository;

    @Override
    public List<Module> getAllModules() {
        return modulesRepository.findAll();
    }

    @Override
    public Module getModuleById(Long id) {
        return modulesRepository.findById(id).orElseThrow(() -> new ServiceException("Module not found"));
    }

    @Override
    public Module createModule(Module module) {
        return modulesRepository.save(module);
    }

    @Override
    public Module updateModule(Long id, Module module) {
        Module existingModule = modulesRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Module not found"));
        existingModule.setLibelle(module.getLibelle());
        existingModule.setDescription(module.getDescription());
        existingModule.setDureeAcquisition(module.getDureeAcquisition());
        return modulesRepository.save(existingModule);
    }

    @Override
    public void deleteModule(Module module) {
        modulesRepository.delete(module);
    }
}