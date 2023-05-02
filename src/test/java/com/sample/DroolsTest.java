package com.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.drools.decisiontable.DecisionTableProviderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

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
import laskuri.Laskuri;

public class DroolsTest {

	Hakija hakija;
	Puoliso puoliso;
	ElakkeensaajanasumistukiHakemus hakemus;
	ElakkeensaajanasumistukiRatkaisu ratkaisu;
	Laskuri laskuri;
	KieServices kieServices;
	KieContainer kieContainer;
	KieSession kieSession;

	@BeforeEach
	void alustus() {
		hakija = new Hakija();
		puoliso = new Puoliso();
		hakemus = new ElakkeensaajanasumistukiHakemus();
		ratkaisu = new ElakkeensaajanasumistukiRatkaisu();
		laskuri = new Laskuri();
		hakemus.setVakiot(new Vakiot());
		hakemus.setLaskuri(laskuri);

		try {

			/*
			 * Sääntömoottorin alustukset
			 */

			this.kieServices = KieServices.Factory.get();
			this.kieContainer = kieServices.getKieClasspathContainer();
			// KieSessionin nimen tulee vastata KieModulessa määritellyn session nimeä
			this.kieSession = kieContainer.newKieSession("ksession-process");

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	
	@Test
	void testitapaus1() {

		// Hakijan tiedot
		hakija.setIka(34);
		hakija.setEtuus(Etuus.TYHJA);
		hakija.setTulot(500);
		hakija.setOmaisuus(500);
		hakija.setVelat(0);

		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);
		hakemus.setHakijallaPuoliso(false);

		// Asunnon tiedot
		Asunto asunto = new Asunto();
		asunto.setSijaintikunta(Kunta.HYVINKÄÄ);
		asunto.setAsuntotyyppi(Asuntotyyppi.VUOKRA_ASUNTO);
		asunto.setAsumismenot(600);

		// Liitetään asunto hakemukseen
		hakemus.setAsunto(asunto);

		// Liitetään hakemus ratkaisupohjaan
		ratkaisu.setHakemus(hakemus);

		// Data eli faktat syötetään sessioon
		kieSession.insert(hakemus);
		kieSession.insert(ratkaisu);

		// Hakemus ja ratkaisu annetaan myös session muuttujiksi
		kieSession.setGlobal("hakemus", hakemus);
		kieSession.setGlobal("ratkaisu", ratkaisu);

		// Käynnistetään prosessi ja aktivoidaan kaikki säännöt
		this.kieSession.startProcess("elakkeensaajanasumistukiprosessi");
		kieSession.fireAllRules();
		kieSession.dispose();

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
		asunto.setAsuntotyyppi(Asuntotyyppi.VUOKRA_ASUNTO);
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

		// printDRL("AsumismenojenEnimmaismaara.xls");

		// Data eli faktat syötetään sessioon
		kieSession.insert(hakemus);
		kieSession.insert(ratkaisu);

		// Hakemus ja ratkaisu annetaan myös session muuttujiksi
		kieSession.setGlobal("hakemus", hakemus);
		kieSession.setGlobal("ratkaisu", ratkaisu);
		// kieSession.setGlobal("laskuri", laskuri);

		// Käynnistetään prosessi ja aktivoidaan kaikki säännöt
		this.kieSession.startProcess("elakkeensaajanasumistukiprosessi");
		kieSession.fireAllRules();
		kieSession.dispose();

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
		asunto.setAsuntotyyppi(Asuntotyyppi.OMAKOTITALO);
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

		// Data eli faktat syötetään sessioon
		kieSession.insert(hakemus);
		kieSession.insert(ratkaisu);

		// Hakemus ja ratkaisu annetaan myös session muuttujiksi
		kieSession.setGlobal("hakemus", hakemus);
		kieSession.setGlobal("ratkaisu", ratkaisu);

		// Käynnistetään prosessi ja aktivoidaan kaikki säännöt
		this.kieSession.startProcess("elakkeensaajanasumistukiprosessi");
		kieSession.fireAllRules();
		kieSession.dispose();

		System.out.println(ratkaisu);
		
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
		asunto.setAsuntotyyppi(Asuntotyyppi.ASUMISOIKEUS_TAI_OSAOMISTUSASUNTO);
		asunto.setAsumismenot(520);

		// Liitetään asunnon tiedot hakemukseen
		hakemus.setAsunto(asunto);

		// Liitetään puolison tiedot hakemukseen
		hakemus.setHakijallaPuoliso(false);

		// Liitetään hakija hakemukseen
		hakemus.setHakija(hakija);

		// Liitetään hakemus ratkaisupohjaan
		ratkaisu.setHakemus(hakemus);

		// Data eli faktat syötetään sessioon
		kieSession.insert(hakemus);
		kieSession.insert(ratkaisu);

		// Hakemus ja ratkaisu annetaan myös session muuttujiksi
		kieSession.setGlobal("hakemus", hakemus);
		kieSession.setGlobal("ratkaisu", ratkaisu);

		// Käynnistetään prosessi ja aktivoidaan kaikki säännöt
		this.kieSession.startProcess("elakkeensaajanasumistukiprosessi");
		kieSession.fireAllRules();
		kieSession.dispose();

		// Tulokset
		assertEquals(393.73, ratkaisu.getMyonnetynTuenMaara());
		assertEquals(true, ratkaisu.isTukiMyonnetty());

	}

	void printDRL(String polku) {
		Resource dt = ResourceFactory.newClassPathResource(polku, getClass());

		DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();

		String drl = decisionTableProvider.loadFromResource(dt, null);
		System.out.println("\n" + drl + "\n");
	}

}
