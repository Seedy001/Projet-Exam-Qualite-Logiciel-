package com.riffo.users.controller;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.service.PartenaireService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partenaires")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PartenaireRESTController {

    private final PartenaireService partenaireService;

    public PartenaireRESTController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @GetMapping
    public ResponseEntity<List<Partenaire>> getAllPartenaires() {
        return ResponseEntity.ok(partenaireService.getAllPartenaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partenaire> getPartenaireById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getPartenaireById(id));
    }

    @GetMapping("/search/nom")
    public ResponseEntity<Partenaire> getPartenaireByNom(@RequestParam String nom) {
        return ResponseEntity.ok(partenaireService.getPartenaireByNom(nom));
    }

    @GetMapping("/search/categorie")
    public ResponseEntity<List<Partenaire>> getPartenairesByCategorie(@RequestParam String categorie) {
        return ResponseEntity.ok(partenaireService.getPartenairesByCategorie(categorie));
    }

    @GetMapping("/search/statut")
    public ResponseEntity<List<Partenaire>> getPartenairesByStatut(@RequestParam String statut) {
        return ResponseEntity.ok(partenaireService.getPartenairesByStatut(statut));
    }

    @GetMapping("/search/ville")
    public ResponseEntity<List<Partenaire>> getPartenairesByVille(@RequestParam String ville) {
        return ResponseEntity.ok(partenaireService.getPartenairesByVille(ville));
    }

    @GetMapping("/search/email")
    public ResponseEntity<Partenaire> getPartenaireByEmail(@RequestParam String email) {
        return ResponseEntity.ok(partenaireService.getPartenaireByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Partenaire> addPartenaire(@Valid @RequestBody Partenaire partenaire) {
        Partenaire nouveau = partenaireService.addPartenaire(partenaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partenaire> updatePartenaire(
            @PathVariable Long id,
            @Valid @RequestBody Partenaire partenaire) {
        return ResponseEntity.ok(partenaireService.updatePartenaire(id, partenaire));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        partenaireService.deletePartenaire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPartenaires() {
        return ResponseEntity.ok(partenaireService.countPartenaires());
    }

    @GetMapping("/exists/email")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(partenaireService.existsByEmail(email));
    }
}
