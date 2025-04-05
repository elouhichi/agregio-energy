package com.agregio.energy.dto;

import com.agregio.energy.model.TypeParc;
import lombok.Builder;

public record ParcDto(String nom, TypeParc type, double capacite) {
}
