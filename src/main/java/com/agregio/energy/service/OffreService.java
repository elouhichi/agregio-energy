package com.agregio.energy.service;

import com.agregio.energy.dto.BlocDto;
import com.agregio.energy.dto.OffreDto;
import com.agregio.energy.mapper.OffreMapper;
import com.agregio.energy.model.Bloc;
import com.agregio.energy.model.Offre;
import com.agregio.energy.model.Parc;
import com.agregio.energy.model.TypeMarche;
import com.agregio.energy.repository.OffreRepository;
import com.agregio.energy.repository.ParcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {

    OffreRepository offreRepository;

    OffreMapper offreMapper;

    ParcRepository parcRepository;

    @Autowired
    public  OffreService (OffreRepository offreRepository , OffreMapper offreMapper , ParcRepository parcRepository){
        this.offreRepository = offreRepository;
        this.offreMapper = offreMapper;
        this.parcRepository = parcRepository;
    }

    /**
     * Cette methode permet de creer une offre
     * @param dto l'offre a créer
     * @return
     */
    public OffreDto createOffre(OffreDto dto) {
        Offre offre = offreMapper.toEntity(dto);

        List<BlocDto> blocDtos = dto.blocs();
        List<Bloc> blocs = offre.getBlocs();

        for (int i = 0; i < blocs.size(); i++) {
            Bloc bloc = blocs.get(i);
            BlocDto blocDto = blocDtos.get(i);

            List<Parc> parcs = parcRepository.findAllById(blocDto.parcIds());

            if (parcs.isEmpty()) {
                throw new IllegalArgumentException("Aucun parc trouvé pour le bloc " + i);
            }

            bloc.setParcs(parcs);
            bloc.setOffre(offre);
        }

        return offreMapper.toDto(offreRepository.save(offre));
    }

    public List<OffreDto> getOffresByMarche(TypeMarche typeMarche){
        List<Offre> offres = offreRepository.findByMarche(typeMarche);
        return offreMapper.toDtoList(offres);
    }
}
