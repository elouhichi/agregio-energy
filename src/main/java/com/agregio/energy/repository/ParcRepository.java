package com.agregio.energy.repository;

import com.agregio.energy.model.Offre;
import com.agregio.energy.model.Parc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcRepository extends JpaRepository<Parc, Long> {
}
