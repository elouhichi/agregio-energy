package com.agregio.energy.dto;

import java.util.List;

public record BlocDto( int heureDebut, int heureFin, double puissanceMW, double prixPlancher, List<Long> parcIds) {
}
