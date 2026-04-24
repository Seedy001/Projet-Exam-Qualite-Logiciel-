package com.riffo.users.service.impl;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.EmailDejaUtiliseException;
import com.riffo.users.exception.PartenaireNotFoundException;
import com.riffo.users.repository.PartenaireRepository;
import com.riffo.users.service.PartenaireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartenaireServiceImpl implements PartenaireService {

    private final PartenaireRepository partenaireRepository;

    public PartenaireServiceImpl(PartenaireRepository partenaireRepository) {
        this.partenaireRepository = partenaireRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getAllPartenaires() {
        return partenaireRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireById(Long id) {
        return partenaireRepository.findById(id)
                .orElseThrow(() -> new PartenaireNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireByNom(String nom) {
        return partenaireRepository.findByNom(nom)
                .orElseThrow(() -> new PartenaireNotFoundException(
                        "Aucun partenaire trouvé avec le nom : " + nom));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByCategorie(String categorie) {
        return partenaireRepository.findByCategorie(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByStatut(String statut) {
        return partenaireRepository.findByStatut(statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByVille(String ville) {
        return partenaireRepository.findByVille(ville);
    }

    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireByEmail(String email) {
        return partenaireRepository.findByEmail(email)
                .orElseThrow(() -> new PartenaireNotFoundException(
                        "Aucun partenaire trouvé avec l'email : " + email));
    }

    @Override
    public Partenaire addPartenaire(Partenaire partenaire) {
        if (partenaire == null) {
            throw new IllegalArgumentException("Le partenaire ne peut pas être null");
        }
        if (partenaireRepository.existsByEmail(partenaire.getEmail())) {
            throw new EmailDejaUtiliseException(partenaire.getEmail());
        }
        return partenaireRepository.save(partenaire);
    }

    @Override
    public Partenaire updatePartenaire(Long id, Partenaire partenaire) {
        if (partenaire == null) {
            throw new IllegalArgumentException("Le partenaire ne peut pas être null");
        }

        Partenaire existant = partenaireRepository.findById(id)
                .orElseThrow(() -> new PartenaireNotFoundException(id));

        existant.setNom(partenaire.getNom());
        existant.setCategorie(partenaire.getCategorie());
        existant.setAdresse(partenaire.getAdresse());
        existant.setVille(partenaire.getVille());
        existant.setTelephone(partenaire.getTelephone());
        existant.setEmail(partenaire.getEmail());
        existant.setLatitude(partenaire.getLatitude());
        existant.setLongitude(partenaire.getLongitude());
        existant.setStatut(partenaire.getStatut());
        existant.setPlafondPriseEnCharge(partenaire.getPlafondPriseEnCharge());

        return partenaireRepository.save(existant);
    }

    @Override
    public void deletePartenaire(Long id) {
        if (!partenaireRepository.existsById(id)) {
            throw new PartenaireNotFoundException(id);
        }
        partenaireRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countPartenaires() {
        return partenaireRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return partenaireRepository.existsByEmail(email);
    }
}
