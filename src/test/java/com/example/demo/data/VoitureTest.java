package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import example.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

package com.example.demo.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoitureTest {

    private Voiture voiture;

    @BeforeEach
    void setUp() {
        // avant chaque test
        voiture = new Voiture("Toyota", 25000);
    }

    @Test
    void testConstructeurEtGetters() {
        
        assertEquals("Toyota", voiture.getMarque());
        assertEquals(25000, voiture.getPrix());
        assertEquals(0, voiture.getId()); // 0 par défaut pour un int
    }

    @Test
    void testConstructeurVide() {
        Voiture voitureVide = new Voiture();
        assertNull(voitureVide.getMarque());
        assertEquals(0, voitureVide.getPrix());
        assertEquals(0, voitureVide.getId());
    }

    @Test
    void testSetId() {
        voiture.setId(10);
        assertEquals(10, voiture.getId());
    }

    @Test
    void testSetMarque() {
        voiture.setMarque("BMW");
        assertEquals("BMW", voiture.getMarque());
    }

    @Test
    void testSetPrix() {
        voiture.setPrix(45000);
        assertEquals(45000, voiture.getPrix());
    }

    @Test
    void testToString() {
        voiture.setId(1);
        String expectedString = "Car{marque='Toyota', prix=25000, id=1}";
        assertEquals(expectedString, voiture.toString());
    }
}


"""
import static org.junit.jupiter.api.Assertions.assertEquals;

import example.util.Calculator;

import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

	private final Calculator calculator = new Calculator();

	@Test
	void addition() {
		assertEquals(2, calculator.add(1, 1));
	}

}
"""