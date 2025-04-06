package com.agregio.energy.mapper;

import com.agregio.energy.dto.ParcDto;
import com.agregio.energy.model.Parc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParcMapper {

    Parc toEntity(ParcDto dto);

    ParcDto toDto(Parc parc);

    List<ParcDto> toDtoList(List<Parc> parcs);

    List<Parc> toEntityList(List<ParcDto> dtos);
}
