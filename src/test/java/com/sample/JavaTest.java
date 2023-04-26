package com.sample;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Asunto;
import data.Asuntotyyppi;
import data.Elakkeensaajanasumistukihakemus;
import data.Etuus;
import data.Hakija;
import data.Puoliso;
import data.Vakiot;
import laskentasovellus.Laskentasovellus;


public class JavaTest {
	

	Hakija hakija;
	Puoliso puoliso;
	Elakkeensaajanasumistukihakemus hakemus;
	
	
	@BeforeEach
	void alustus() {
		hakija = new Hakija();
		puoliso = new Puoliso();
		hakemus = new Elakkeensaajanasumistukihakemus();
		hakemus.setVakiot(new Vakiot());
		
	}
	
	
	@Test
	void testitapaus1() {
		
		// Hakijan tiedot
		hakija.setIka(34);
		
		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);
		hakemus.setHakijallaPuoliso(false);
		
		// Prosessoidaan hakemus
		Laskentasovellus laskentasovellus = new Laskentasovellus();
		laskentasovellus.kasitteleHakemus(hakemus);
				
		// Assertit
		assertEquals(hakemus.isHakemusHyvaksytty(), false);
	}
	
	@Test
	void testitapaus2() {
		
		// Hakijan tiedot
		hakija.setIka(67);
		hakija.setEtuus(Etuus.VANHUUSELAKE);
		hakija.setTulot(600);
		hakija.setOmaisuus(1400);
		hakija.setVelat(0);
		
		// Asunnon tiedot
		Asunto asunto = new Asunto();
		asunto.setSijaintikunta("Helsinki");
		asunto.setAsunnontyyppi(Asuntotyyppi.VUOKRA_ASUNTO);
		asunto.setAsumismenot(770);
		hakija.setAsunto(asunto);
		
		// Puolison tiedot
		puoliso.setIka(65);
		puoliso.setEtuus(Etuus.VANHUUSELAKE);
		puoliso.setTulot(500);
		puoliso.setOmaisuus(3200);
		puoliso.setVelat(0);
		
		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);
		
		// Prosessoidaan hakemus
		Laskentasovellus laskentasovellus = new Laskentasovellus();
		laskentasovellus.kasitteleHakemus(hakemus);
		
		// Assertit
		
		
	}
	
	@Test
	void testitapaus3() {
		
	}
	
	@Test
	void testitapaus4() {
		
	}
	
	
	

}
