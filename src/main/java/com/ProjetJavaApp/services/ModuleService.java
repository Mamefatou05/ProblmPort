package com.ProjetJavaApp.services;

import com.ProjetJavaApp.data.entities.Module;

import java.util.List;

public interface ModuleService {
    List<Module> getAllModules();
    Module getModuleById(Long id);
    Module createModule(Module module);
    Module updateModule(Long id, Module module);
    void deleteModule(Module module) ;
}