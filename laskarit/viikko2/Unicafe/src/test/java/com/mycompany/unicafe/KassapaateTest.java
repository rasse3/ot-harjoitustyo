/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rasmus
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    @Before
    public void setUp(){
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }
    
    
    @Test
    public void alkusaldoOikein(){
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void myydytEdullisetAlussaNolla(){
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytMaukkaatAlussaNolla(){
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
     public void riittavaMaksuToimiiMaukkaasti(){
         kassapaate.syoMaukkaasti(400);
         assertEquals(100400, kassapaate.kassassaRahaa());
         assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
     }
     
     @Test
     public void riittamatonMaksuToimiiMaukkaasti(){
         
         
         int vaihtorahat = kassapaate.syoMaukkaasti(250);
         assertEquals(250, vaihtorahat);
         assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
         assertEquals(100000, kassapaate.kassassaRahaa());
         
         
         
     }
     
     
     @Test
      public void riittavaMaksuToimiiEdullisesti(){
         kassapaate.syoEdullisesti(400);
         assertEquals(100240, kassapaate.kassassaRahaa());
         assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
     }
     
     @Test
     public void riittamatonMaksuToimiiEdullisesti(){
         
         
         int vaihtorahat = kassapaate.syoEdullisesti(230);
         assertEquals(230, vaihtorahat);
         assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
         assertEquals(100000, kassapaate.kassassaRahaa());
         
         
         
     }
     
     
     
     @Test
     public void korttiOstoToimiiMaukkaidenOsalta(){
         
         boolean onnistuiko  = kassapaate.syoMaukkaasti(maksukortti);
         assert(onnistuiko);
         assertEquals(600, maksukortti.saldo());
         assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
         assertEquals(100000, kassapaate.kassassaRahaa());
     }
     
     
     @Test 
     public void korttiOstoEiToimiJosEiRahaaMaukkaasti(){
         maksukortti.otaRahaa(650);
         boolean onnistuiko = kassapaate.syoMaukkaasti(maksukortti);
         assert(!onnistuiko);
         assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
         assertEquals(350, maksukortti.saldo());
         assertEquals(100000, kassapaate.kassassaRahaa());
     }
    
      @Test
     public void korttiOstoToimiiEdullistenOsalta(){
         
         boolean onnistuiko  = kassapaate.syoEdullisesti(maksukortti);
         assert(onnistuiko);
         assertEquals(760, maksukortti.saldo());
         assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
         assertEquals(100000, kassapaate.kassassaRahaa());
     }
     
     
     @Test 
     public void korttiOstoEiToimiJosEiRahaaEdullisesti(){
         maksukortti.otaRahaa(850);
         boolean onnistuiko = kassapaate.syoEdullisesti(maksukortti);
         assert(!onnistuiko);
         assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
         assertEquals(150, maksukortti.saldo());
         assertEquals(100000, kassapaate.kassassaRahaa());
     }
     
     @Test
     public void rahanLatausKasvattaaSaldoaJaKassaa(){
         kassapaate.lataaRahaaKortille(maksukortti, 500);
         assertEquals(1500, maksukortti.saldo());
         assertEquals(100500, kassapaate.kassassaRahaa());
         
     
         
     }
     
     @Test
     public void negatiivisenTaiNollanLatausKortille(){
         kassapaate.lataaRahaaKortille(maksukortti, -110);
         assertEquals(1000, maksukortti.saldo());
     }
     
     
}
