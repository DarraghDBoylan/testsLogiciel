package com.example.demo.service;

import com.example.demo.data.Voiture; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // appel mockito
class VoitureServiceTest {

    @Mock
    private VoitureRepository voitureRepository; // "Mock"

    @InjectMocks
    private VoitureService voitureService; 

    private List<Voiture> listeDeVoitures;

    @BeforeEach
    void setUp() {
        // données bidon before each
        listeDeVoitures = List.of(
                new Voiture("Peugeot", 20000),
                new Voiture("Renault", 30000)
        );
    }

    @Test
    void testCalculerEchantillon() {
        //ARRANGE 
        // demance de renvoyer notre fausse liste
        when(voitureRepository.findAll()).thenReturn(listeDeVoitures);

        // ACT 
        // execution de methode
        Echantillon echantillonResultat = voitureService.obtenirStatistiquesEchantillon();

        // 3. ASSERT ((verif)
        assertNotNull(echantillonResultat, "L'échantillon ne devrait pas être nul");
        
        
        assertEquals(2, echantillonResultat.getNombreDeVoitures());
        
       
        assertEquals(25000, echantillonResultat.getPrixMoyen());

       
        verify(voitureRepository, times(1)).findAll();
    }
}
