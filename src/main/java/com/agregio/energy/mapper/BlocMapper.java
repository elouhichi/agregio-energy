package com.agregio.energy.mapper;

import com.agregio.energy.dto.BlocDto;
import com.agregio.energy.model.Bloc;
import com.agregio.energy.model.Parc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ParcMapper.class)
public interface BlocMapper {

    @Mapping(target = "parcs", ignore = true)
    Bloc toEntity(BlocDto dto);

    @Mapping(target = "parcIds", expression = "java(toParcIdList(bloc.getParcs()))")
    BlocDto toDto(Bloc bloc);

    default List<Long> toParcIdList(List<Parc> parcs) {
        return parcs == null ? List.of() : parcs.stream().map(Parc::getId).collect(Collectors.toList());
    }

    List<BlocDto> toDtoList(List<Bloc> blocs);
}
