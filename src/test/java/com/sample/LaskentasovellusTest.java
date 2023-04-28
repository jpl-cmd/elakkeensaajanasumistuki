package com.sample;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import data.Asunto;
import data.Asuntotyyppi;
import data.ElakkeensaajanasumistukiHakemus;
import data.ElakkeensaajanasumistukiRatkaisu;
import data.Etuus;
import data.Hakija;
import data.Kunta;
import data.Puoliso;
import data.Vakiot;
import laskentasovellus.Laskentasovellus;


public class LaskentasovellusTest {
	

	Hakija hakija;
	Puoliso puoliso;
	ElakkeensaajanasumistukiHakemus hakemus;
	ElakkeensaajanasumistukiRatkaisu ratkaisu;
	
	
	@BeforeEach
	void alustus() {
		hakija = new Hakija();
		puoliso = new Puoliso();
		hakemus = new ElakkeensaajanasumistukiHakemus();
		ratkaisu = new ElakkeensaajanasumistukiRatkaisu();
		hakemus.setVakiot(new Vakiot());
		
	}
	
	@Disabled
	@Test
	void testitapaus1() {
		
		// Hakijan tiedot
		hakija.setIka(34);
		
		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);
		hakemus.setHakijallaPuoliso(false);
		
		// Liitetään hakemus ratkaisupohjaan
		ratkaisu.setHakemus(hakemus);
		
		// Prosessoidaan hakemus
		Laskentasovellus laskentasovellus = new Laskentasovellus();
		laskentasovellus.teeRatkaisu(ratkaisu);
		
		ratkaisu.setTukiMyonnetty(false);
		
		// Tulokset
		assertEquals(0.0, ratkaisu.getMyonnetynTuenMaara());
		assertEquals(false , ratkaisu.isTukiMyonnetty());
		
		System.out.println(ratkaisu.isTukiMyonnetty());
		System.out.println(ratkaisu.getMyonnetynTuenMaara());
		
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
		asunto.setSijaintikunta(Kunta.HELSINKI);
		asunto.setAsunnontyyppi(Asuntotyyppi.VUOKRA_ASUNTO);
		asunto.setAsumismenot(770);
		
		// Liitetään asunnon tiedot hakemukseen
		hakemus.setAsunto(asunto);
		
		// Puolison tiedot
		puoliso.setIka(65);
		puoliso.setEtuus(Etuus.VANHUUSELAKE);
		puoliso.setTulot(500);
		puoliso.setOmaisuus(3200);
		puoliso.setVelat(0);
		
		// Liitetään puolison tiedot hakemukseen
		hakemus.setPuoliso(puoliso);
		hakemus.setHakijallaPuoliso(true);
		
		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);
		
		// Liitetään hakemus ratkaisupohjaan
		ratkaisu.setHakemus(hakemus);
		
		// Prosessoidaan hakemus
		Laskentasovellus laskentasovellus = new Laskentasovellus();
		laskentasovellus.teeRatkaisu(ratkaisu);
		
		// Tulokset
		//assertEquals(0.0, ratkaisu.getMyonnetynTuenMaara());
		//assertEquals(false , ratkaisu.isTukiMyonnetty());
		System.out.println(ratkaisu);
		
		System.out.println(ratkaisu.isTukiMyonnetty());
		System.out.println(ratkaisu.getMyonnetynTuenMaara());
		
		
	}
	
	@Disabled
	@Test
	void testitapaus3() {
		
	}
	
	@Disabled
	@Test
	void testitapaus4() {
		
	}
	
	
	

}
