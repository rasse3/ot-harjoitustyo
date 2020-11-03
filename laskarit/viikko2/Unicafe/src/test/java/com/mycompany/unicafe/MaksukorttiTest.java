package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
}
    
    @Test
    public void rahanLisaysToimii(){
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void rahanOttoToimii(){
        kortti.lataaRahaa(300);
        kortti.otaRahaa(200);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void ottoKunRahatEiRiita(){
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void palauttaakoTrueKunOtetaan(){
        boolean apu = kortti.otaRahaa(10);
        assertTrue(apu);
    }
    
    @Test
    public void palauttaakoFalseKunEiSaada(){
        boolean apu = kortti.otaRahaa(100);
        assertFalse(apu);
    }
    
    @Test
    public void saldonTestaus(){
        assertEquals(kortti.saldo(), 10);
    }
    
}
