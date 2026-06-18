package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.Statistique;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StatistiqueController.class) 
class StatistiqueControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private Statistique statistique; 

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetStatistiques_Succes() throws Exception {
        //  ARRANGE 
        Echantillon fauxEchantillon = new Echantillon(5, 20000);
        when(statistique.prixMoyen()).thenReturn(fauxEchantillon);

        //  ACT & ASSERT 
        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk()) // Vérifie le code HTTP 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Vérifie json
                .andExpect(jsonPath("$.nombreDeVoitures").value(5)) 
                .andExpect(jsonPath("$.prixMoyen").value(20000));

        verify(statistique, times(1)).prixMoyen();
    }


    @Test
    void testGetStatistiques_PasDeVoitureException() throws Exception {
        // ARRANGE 
        when(statistique.prixMoyen()).thenThrow(new ArithmeticException("Division par zéro"));

        // ACT & ASSERT 

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isInternalServerError()); // Ou le code lié à votre gestion d'exception
    }





    @Test
    void testCreerVoiture() throws Exception {
        // ARRANGE 
        Voiture nouvelleVoiture = new Voiture("Tesla", 45000);
        String jsonVoiture = objectMapper.writeValueAsString(nouvelleVoiture);

        // ACT + ASSERT 
        mockMvc.perform(post("/voiture")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonVoiture))
                .andExpect(status().isOk()); // Vérifie qu e la création retourne un code 200
        verify(statistique, times(1)).ajouter(any(Voiture.class));
    }
}


