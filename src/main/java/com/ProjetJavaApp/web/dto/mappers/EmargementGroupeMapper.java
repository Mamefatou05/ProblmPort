package com.ProjetJavaApp.web.dto.mappers;

import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.data.enums.TypeEmargEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmargementGroupeMapper {

    @Mapping(target = "dateEntree", source = "dateEmargement")
    @Mapping(target = "apprenant.id", source = "apprenantId")  // Mapping spécifique pour l'apprenant
    Emergement toEntity(Long apprenantId, Date dateEmargement, TypeEmargEnum typeEmargement);

    default List<Emergement> toEntityList(Date dateEmargement, List<Long> apprenantIds, TypeEmargEnum typeEmargement) {
        return apprenantIds.stream()
                .map(apprenantId -> toEntity(apprenantId, dateEmargement, typeEmargement))
                .collect(Collectors.toList());
    }
}
