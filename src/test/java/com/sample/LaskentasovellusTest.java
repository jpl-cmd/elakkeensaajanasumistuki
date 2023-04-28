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

	@Test
	void testitapaus1() {

		// Hakijan tiedot
		hakija.setIka(34);
		hakija.setEtuus(Etuus.TYHJA);
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
		assertEquals(false, ratkaisu.isTukiMyonnetty());
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
		assertEquals(303.11, ratkaisu.getMyonnetynTuenMaara());
		assertEquals(true, ratkaisu.isTukiMyonnetty());
	}

	@Test
	void testitapaus3() {
		// Hakijan tiedot
		hakija.setIka(52);
		hakija.setEtuus(Etuus.TAKUUELAKE);
		hakija.setTulot(500);
		hakija.setOmaisuus(2000);
		hakija.setVelat(500);

		// Asunnon tiedot
		Asunto asunto = new Asunto();
		asunto.setSijaintikunta(Kunta.HARTOLA);
		asunto.setAsunnontyyppi(Asuntotyyppi.OMAKOTITALO);
		asunto.setPintaAla(120);
		asunto.setValmistunutTaiPerusparannettuEnnen1974(true);
		asunto.setAsumismenot(8.33);

		// Liitetään asunnon tiedot hakemukseen
		hakemus.setAsunto(asunto);

		// Puolison tiedot
		puoliso.setIka(57);
		puoliso.setEtuus(Etuus.TYHJA);
		puoliso.setTulot(1500);
		puoliso.setOmaisuus(3500);
		puoliso.setVelat(1000);

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
		assertEquals(0.0, ratkaisu.getMyonnetynTuenMaara());
		assertEquals(false, ratkaisu.isTukiMyonnetty());

	}

	
	@Test
	void testitapaus4() {

		// Hakijan tiedot
		hakija.setIka(45);
		hakija.setEtuus(Etuus.TYOKYVYTTOMYYSELAKE);
		hakija.setTulot(400);
		hakija.setOmaisuus(5000);
		hakija.setVelat(1200);

		// Asunnon tiedot
		Asunto asunto = new Asunto();
		asunto.setSijaintikunta(Kunta.HAMINA);
		asunto.setAsunnontyyppi(Asuntotyyppi.ASUMISOIKEUS_TAI_OSAOMISTUSASUNTO);
		asunto.setAsumismenot(520);
		
		// Liitetään asunnon tiedot hakemukseen
		hakemus.setAsunto(asunto);

		// Liitetään puolison tiedot hakemukseen
		hakemus.setHakijallaPuoliso(false);

		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);

		// Liitetään hakemus ratkaisupohjaan
		ratkaisu.setHakemus(hakemus);

		// Prosessoidaan hakemus
		Laskentasovellus laskentasovellus = new Laskentasovellus();
		laskentasovellus.teeRatkaisu(ratkaisu);

		// Tulokset
		assertEquals(393.73, ratkaisu.getMyonnetynTuenMaara());
		assertEquals(true, ratkaisu.isTukiMyonnetty());

	}

}
