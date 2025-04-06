package com.agregio.energy.controller;

import com.agregio.energy.dto.OffreDto;
import com.agregio.energy.model.TypeMarche;
import com.agregio.energy.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offres")
@RequiredArgsConstructor
public class OffreController {


    private final OffreService offreService;

    @PostMapping
    public ResponseEntity<OffreDto> create(@RequestBody OffreDto dto) {
        if (dto.blocs() == null || dto.blocs().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        OffreDto created = offreService.createOffre(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OffreDto>> findByMarche(@RequestParam TypeMarche marche) {
        List<OffreDto> offres = offreService.getOffresByMarche(marche);
        return ResponseEntity.ok(offres);
    }
}
