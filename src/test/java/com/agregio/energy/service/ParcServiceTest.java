package com.agregio.energy.service;

import com.agregio.energy.dto.ParcDto;
import com.agregio.energy.mapper.ParcMapper;
import com.agregio.energy.model.*;
import com.agregio.energy.repository.OffreRepository;
import com.agregio.energy.repository.ParcRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParcServiceTest {

    ParcService parcService;

    @Mock
    OffreRepository offreRepository;

    @Mock
    ParcRepository parcRepository;

    ParcMapper parcMapper = Mappers.getMapper(ParcMapper.class);

    @BeforeEach
    void setUp() {
        parcService = new ParcService(offreRepository, parcRepository, parcMapper);
    }


    @Test
    void createParcSuccess() {
   ParcDto dto = new ParcDto("parc 1" , TypeParc.HYDRAULIQUE , 50.2);
        when(parcRepository.save(any(Parc.class))).thenReturn(parcMapper.toEntity(dto));

        ParcDto result = parcService.createParc(dto);

        assertNotNull(result);
        assertEquals("parc 1", result.nom());
        assertEquals(TypeParc.HYDRAULIQUE, result.type());
        assertEquals(50.2, result.capacite());

    }

    @Test
    void capaciteFailNegative() {
        ParcDto dto = new ParcDto("Parc capacité negative", TypeParc.SOLAIRE, -30.0);

        assertThrows(IllegalArgumentException.class, () -> parcService.createParc(dto));
    }

    @Test
    void parcByTypeMarcheSuccess(){
        Parc parc1 = Parc.builder().nom("parc 2").type(TypeParc.HYDRAULIQUE).capacite(120.24).build();
        Bloc bloc1 = Bloc.builder().parcs(List.of(parc1)).build();
        List<Bloc> blocs = List.of(bloc1);
        Offre offre = Offre.builder().marche(TypeMarche.SECONDAIRE).blocs(blocs).build();
        when(offreRepository.findByMarche(TypeMarche.SECONDAIRE)).thenReturn(List.of(offre));
        List<ParcDto> results = parcService.getParcsByMarche(TypeMarche.SECONDAIRE);
        Assertions.assertEquals(1 , results.size());
    }
}
