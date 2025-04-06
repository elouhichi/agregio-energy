package com.agregio.energy.dto;

import com.agregio.energy.model.TypeMarche;

import java.util.List;
public record OffreDto (TypeMarche marche, List<BlocDto> blocs) {
}
