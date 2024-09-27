package com.ProjetJavaApp.web.dto.mappers;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.web.dto.requests.PromotionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends IMapper<PromotionDTO, Promotion> {
}
