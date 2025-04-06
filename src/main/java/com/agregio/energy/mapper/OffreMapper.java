package com.agregio.energy.mapper;

import com.agregio.energy.dto.OffreDto;
import com.agregio.energy.model.Offre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BlocMapper.class, ParcMapper.class})
public interface OffreMapper {

    Offre toEntity(OffreDto dto);

    OffreDto toDto(Offre entity);

    List<OffreDto> toDtoList(List<Offre> entities);
}
