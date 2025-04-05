package com.agregio.energy.repository;

import com.agregio.energy.model.Offre;
import com.agregio.energy.model.TypeMarche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {

    List<Offre> findByMarche(TypeMarche typeMarche);
}
