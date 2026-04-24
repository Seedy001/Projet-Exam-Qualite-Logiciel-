package com.riffo.users.controller;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.GlobalExceptionHandler;
import com.riffo.users.exception.PartenaireNotFoundException;
import com.riffo.users.service.PartenaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PartenaireRESTControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PartenaireService partenaireService;

    private Partenaire partenaire;

    @BeforeEach
    void setUp() {
        PartenaireRESTController controller = new PartenaireRESTController(partenaireService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        partenaire = new Partenaire(
                "Clinique Santé Plus", "SANTE", "12 Avenue Bourguiba", "Dakar",
                "+221771234567", "contact@santeplus.sn",
                14.6928, -17.4467, "ACTIF", 500000.0
        );
        partenaire.setId(1L);
    }

    // Test 1 : POST /partenaires
    @Test
    void testPostPartenaire() throws Exception {
        when(partenaireService.addPartenaire(any(Partenaire.class))).thenReturn(partenaire);

        String json = "{\"nom\":\"Clinique Santé Plus\",\"categorie\":\"SANTE\","
                + "\"adresse\":\"12 Avenue Bourguiba\",\"ville\":\"Dakar\","
                + "\"telephone\":\"+221771234567\",\"email\":\"contact@santeplus.sn\","
                + "\"latitude\":14.6928,\"longitude\":-17.4467,"
                + "\"statut\":\"ACTIF\",\"plafondPriseEnCharge\":500000.0}";

        mockMvc.perform(post("/partenaires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Clinique Santé Plus"));
    }

    // Test 2 : GET /partenaires
    @Test
    void testGetPartenaires() throws Exception {
        when(partenaireService.getAllPartenaires()).thenReturn(List.of(partenaire));

        mockMvc.perform(get("/partenaires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Clinique Santé Plus"));
    }

    // Test 3 : DELETE /partenaires/{id}
    @Test
    void testDeletePartenaire() throws Exception {
        doNothing().when(partenaireService).deletePartenaire(1L);

        mockMvc.perform(delete("/partenaires/1"))
                .andExpect(status().isNoContent());
    }

    // Test 4 : DELETE /partenaires/{id} — partenaire inexistant → 404
    @Test
    void testDeletePartenaireInexistant() throws Exception {
        doThrow(new PartenaireNotFoundException(99L))
                .when(partenaireService).deletePartenaire(99L);

        mockMvc.perform(delete("/partenaires/99"))
                .andExpect(status().isNotFound());
    }
}
