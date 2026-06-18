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
        // 1. ARRANGE : On prépare un faux échantillon que le service va retourner
        Echantillon fauxEchantillon = new Echantillon(5, 20000);
        when(statistique.prixMoyen()).thenReturn(fauxEchantillon);

        // 2. ACT & ASSERT : On simule le GET /statistique
        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk()) // Vérifie le code HTTP 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Vérifie que c'est du JSON
                .andExpect(jsonPath("$.nombreDeVoitures").value(5)) // Vérifie le contenu JSON
                .andExpect(jsonPath("$.prixMoyen").value(20000));

        verify(statistique, times(1)).prixMoyen();
    }