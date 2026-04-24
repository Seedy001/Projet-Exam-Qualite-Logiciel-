package com.riffo.users.service;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.EmailDejaUtiliseException;
import com.riffo.users.exception.PartenaireNotFoundException;
import com.riffo.users.repository.PartenaireRepository;
import com.riffo.users.service.impl.PartenaireServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartenaireServiceImplTest {

    @Mock
    private PartenaireRepository partenaireRepository;

    @InjectMocks
    private PartenaireServiceImpl partenaireService;

    private Partenaire partenaire;

    @BeforeEach
    void setUp() {
        partenaire = new Partenaire(
                "Clinique Santé Plus",
                "SANTE",
                "12 Avenue Bourguiba",
                "Dakar",
                "+221771234567",
                "contact@santeplus.sn",
                14.6928, -17.4467,
                "ACTIF",
                500000.0
        );
        partenaire.setId(1L);
    }

    // Test 1 : création d'un partenaire
    @Test
    void testCreerPartenaire() {
        when(partenaireRepository.existsByEmail(partenaire.getEmail())).thenReturn(false);
        when(partenaireRepository.save(partenaire)).thenReturn(partenaire);

        Partenaire result = partenaireService.addPartenaire(partenaire);

        assertNotNull(result);
        assertEquals("Clinique Santé Plus", result.getNom());
        verify(partenaireRepository).save(partenaire);
    }

    // Test 2 : recherche par ID
    @Test
    void testRechercheParId() {
        when(partenaireRepository.findById(1L)).thenReturn(Optional.of(partenaire));

        Partenaire result = partenaireService.getPartenaireById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clinique Santé Plus", result.getNom());
    }

    // Test 3 : email déjà utilisé
    @Test
    void testEmailDejaUtilise() {
        when(partenaireRepository.existsByEmail(partenaire.getEmail())).thenReturn(true);

        assertThrows(EmailDejaUtiliseException.class, () -> {
            partenaireService.addPartenaire(partenaire);
        });
    }

    // Test 4 : cas d'erreur — partenaire inexistant
    @Test
    void testPartenaireInexistant() {
        when(partenaireRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PartenaireNotFoundException.class, () -> {
            partenaireService.getPartenaireById(99L);
        });
    }
}
