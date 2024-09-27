package com.ProjetJavaApp.web.dto.mappers;

import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.web.dto.requests.emargements.EmargementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EmargementMapper extends IMapper<EmargementDTO, Emergement> {

    // Mapper une entité vers un DTO
    @Mapping(target = "apprenantId", source = "apprenant.id")
    EmargementDTO toDto(Emergement entity);


    // Mapper une liste d'entités vers une liste de DTOs
    List<EmargementDTO> toDtoList(List<Emergement> entityList);


}
