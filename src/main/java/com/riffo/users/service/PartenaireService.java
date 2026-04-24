package com.riffo.users.service;

import com.riffo.users.entity.Partenaire;
import java.util.List;

public interface PartenaireService {

    List<Partenaire> getAllPartenaires();

    Partenaire getPartenaireById(Long id);

    Partenaire getPartenaireByNom(String nom);

    List<Partenaire> getPartenairesByCategorie(String categorie);

    List<Partenaire> getPartenairesByStatut(String statut);

    List<Partenaire> getPartenairesByVille(String ville);

    Partenaire getPartenaireByEmail(String email);

    Partenaire addPartenaire(Partenaire partenaire);

    Partenaire updatePartenaire(Long id, Partenaire partenaire);

    void deletePartenaire(Long id);

    long countPartenaires();

    boolean existsByEmail(String email);
}
