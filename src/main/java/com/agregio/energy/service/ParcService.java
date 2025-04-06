package com.agregio.energy.service;

import com.agregio.energy.dto.ParcDto;
import com.agregio.energy.mapper.ParcMapper;
import com.agregio.energy.model.Offre;
import com.agregio.energy.model.Parc;
import com.agregio.energy.model.TypeMarche;
import com.agregio.energy.repository.OffreRepository;
import com.agregio.energy.repository.ParcRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcService {

    OffreRepository offreRepository;

    ParcRepository parcRepository;

    ParcMapper parcMapper;

    public ParcService(OffreRepository offreRepository, ParcRepository parcRepository , ParcMapper parcMapper){
        this.parcMapper = parcMapper;
        this.parcRepository = parcRepository;
        this.offreRepository = offreRepository;
    }

    public ParcDto createParc(ParcDto dto) {
        if (dto.capacite() <= 0) {
            throw new IllegalArgumentException("La capacité doit être supérieure à zéro");
        }
        Parc parcToSave = parcMapper.toEntity(dto);
        return parcMapper.toDto(parcRepository.save(parcToSave));
    }

    public List<ParcDto> getParcsByMarche(TypeMarche typeMarche) {
        List<Offre> offres = offreRepository.findByMarche(typeMarche);
        List<Parc> parcs = offres.stream().flatMap(offre -> offre.getBlocs().stream())
                .flatMap(bloc -> bloc.getParcs().stream())
                .distinct()
                .collect(Collectors.toList());
        return parcMapper.toDtoList(parcs);
    }
}
