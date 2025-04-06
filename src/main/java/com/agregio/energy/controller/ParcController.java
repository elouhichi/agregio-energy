package com.agregio.energy.controller;

import com.agregio.energy.dto.ParcDto;
import com.agregio.energy.model.TypeMarche;
import com.agregio.energy.service.ParcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcs")
@RequiredArgsConstructor
public class ParcController {

    private final ParcService parcService;

    @PostMapping
    public ResponseEntity<ParcDto> createParc(@RequestBody ParcDto parcDto) {
        ParcDto createdParc = parcService.createParc(parcDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParc);
    }

    @GetMapping
    public ResponseEntity<List<ParcDto>> getParcsByMarche(@RequestParam TypeMarche marche) {
        List<ParcDto> parcs = parcService.getParcsByMarche(marche);
        return ResponseEntity.ok(parcs);
    }
}
