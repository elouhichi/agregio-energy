package com.agregio.energy.service;

import com.agregio.energy.dto.BlocDto;
import com.agregio.energy.dto.OffreDto;
import com.agregio.energy.model.*;
import com.agregio.energy.repository.OffreRepository;
import com.agregio.energy.repository.ParcRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OffreServiceTest {
    @InjectMocks
    private OffreService offreService;

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private ParcRepository parcRepository;

    @Test
    void createOffreSuccess() {
        BlocDto blocDto = new BlocDto(0, 3, 55.2, 23.0, List.of(1L));
        OffreDto offreDto = new OffreDto(TypeMarche.PRIMAIRE, List.of(blocDto));

        Parc parc = new Parc(1L, "Parc 1", TypeParc.EOLIEN, 100.0);
        Bloc bloc = Bloc.builder().heureDebut(0).heureFin(3).puissanceMW(55.2).prixPlancher(23.0).parcs(List.of(parc)).build();
        Offre savedOffre = Offre.builder().id(1L).marche(TypeMarche.PRIMAIRE).blocs(List.of(bloc)).build();

        when(parcRepository.findAllById(List.of(1L))).thenReturn(List.of(parc));
        when(offreRepository.save(any(Offre.class))).thenReturn(savedOffre);

        OffreDto result = offreService.createOffre(offreDto);

        assertEquals(TypeMarche.PRIMAIRE, result.marche());
        assertEquals(1, result.blocs().size());
        assertEquals(55.2, result.blocs().get(0).puissanceMW());
    }
}
