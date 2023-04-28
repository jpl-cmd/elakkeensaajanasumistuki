package laskentasovellus;

import java.time.LocalDate;

import data.ElakkeensaajanasumistukiHakemus;
import data.ElakkeensaajanasumistukiRatkaisu;
import data.Etuus;
import data.Hakija;
import data.Kunta;
import data.Kuntaryhma;
import data.LammitysRyhma;
import data.Vakiot;
import util.PvmTarkistus;

public class PaattelyMoottori {

	LocalDate alkupvm = LocalDate.parse("2023-01-01");
	LocalDate loppupvm = LocalDate.parse("2023-12-31");

	/*
	 * VAKIOIDEN ASETTAMINEN
	 */

	public void paatteleVakiot(ElakkeensaajanasumistukiHakemus hakemus) {
		Vakiot vakiot = hakemus.getVakiot();

		vakiot.setElakkeensaajanasumistukiKerroin(0.85);
		vakiot.setPerusomavastuu(56.78);
		vakiot.setLisaomavastuuKerroin(0.413);
		vakiot.setOmaisuusKerroin(0.08);
		vakiot.setOmakotitalonVesimaksunEnimmaismaara(32.40);
	}

	/*
	 * LOPPUTULOKSEEN VAIKUTTAVA PÄÄTTELY
	 */

	public void paattelePieninMaksettavaAsumistuki(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.isHakijallaPuoliso() && hakemus.isPuolisollaOikeusAsumistukeen()) {
				hakemus.setPieninMaksettavaAsumistuki(7.46);
			} else {
				hakemus.setPieninMaksettavaAsumistuki(3.73);
			}

		}

	}

	/*
	 * LISÄOMAVASTUUSEEN VAIKUTTAVAT PÄÄTTELYT
	 */

	public void paatteleOmaisuusRaja(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.isHakijallaPuoliso()) {
				hakemus.setOmaisuusRaja(29290);
			} else {
				hakemus.setOmaisuusRaja(18306);
			}

		}

	}

	public void paatteleOnkoHakijallaOikeusAsumistukeen(ElakkeensaajanasumistukiHakemus hakemus) {
		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getHakija().getIka() >= 16) {
				
				if (hakemus.getHakija().getEtuus().equals(Etuus.ANSIONMENETYKSEN_KORVAUS)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.ELINKORKO)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.HUOLTOELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_KUNTOUTUSRAHA)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_TAPATURMAELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_TYOKYVYTTOMYYSELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.LESKENELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.TAKUUELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.TYOKYVYTTOMYYSELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.TYOURAELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.VANHUUSELAKE)
						|| hakemus.getPuoliso().getEtuus().equals(Etuus.VASTAAVA_ULKOMAILTA_MAKSETTAVA_ETUUS)) {

					hakemus.setHakijallaOikeusAsumistukeen(true);

				} else {
					hakemus.setHakijallaOikeusAsumistukeen(false);
				}

			} else {

				hakemus.setHakijallaOikeusAsumistukeen(false);

			}

		}

	}

	public void paatteleOnkoPuolisollaOikeusAsumistukeen(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.isHakijallaPuoliso()) {

				if (hakemus.getPuoliso().getIka() >= 16) {

					if (hakemus.getPuoliso().getEtuus().equals(Etuus.ANSIONMENETYKSEN_KORVAUS)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.ELINKORKO)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.HUOLTOELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_KUNTOUTUSRAHA)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_TAPATURMAELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.JATKUVA_TYOKYVYTTOMYYSELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.LESKENELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.TAKUUELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.TYOKYVYTTOMYYSELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.TYOURAELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.VANHUUSELAKE)
							|| hakemus.getPuoliso().getEtuus().equals(Etuus.VASTAAVA_ULKOMAILTA_MAKSETTAVA_ETUUS)) {

						hakemus.setPuolisollaOikeusAsumistukeen(true);

					} else {
						hakemus.setPuolisollaOikeusAsumistukeen(false);
					}

				} else {

					hakemus.setPuolisollaOikeusAsumistukeen(false);

				}

			}

		}

	}

	public void paatteleLisaomavastuunTuloraja(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.isHakijallaPuoliso() && hakemus.isPuolisollaOikeusAsumistukeen()) {
				hakemus.setLisaomavastuunTuloraja(16783);
			} else if (hakemus.isHakijallaPuoliso()) {
				hakemus.setLisaomavastuunTuloraja(14746);
			} else if (!hakemus.isHakijallaPuoliso()) {
				hakemus.setLisaomavastuunTuloraja(10280);
			}

		}

	}

	/*
	 * ASUMISMENOIHIN LIITTYVÄT PÄÄTTELYT
	 */

	public void paatteleLammitysryhma(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getAsunto().getSijaintikunta().equals(Kunta.ASKOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.AURA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ESPOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.EURA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.EURAJOKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HAMINA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HANKO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HARJAVALTA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HELSINKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HUITTINEN)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HYVINKÄÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.IITTI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.IMATRA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.INKOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.JÄMIJÄRVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.JÄRVENPÄÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KAARINA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KANKAANPÄÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KARKKILA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KARVIA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KAUNIAINEN)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KEMIÖNSAARI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KERAVA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KIRKKONUMMI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KOKEMÄKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KOSKI_TL)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KOTKA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KOUVOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KUSTAVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.LAITILA)) {
				// Loput rivit jätetty pois

				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_1);

			} else if (hakemus.getAsunto().getSijaintikunta().equals(Kunta.AKAA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ALAJÄRVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ALAVUS)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ASIKKALA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ENONKOSKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.EVIJÄRVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.FORSSA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HALSUA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HARTOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HATTULA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HAUSJÄRVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HEINOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HEINÄVESI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HIRVENSALMI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HOLLOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HUMPPILA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HÄMEENKYRÖ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HÄMEENLINNA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.IKAALINEN)) {
				// Loput rivit jätetty pois

				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_2);

			} else {

				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_3);

			}

		}

	}

	public void paatteleLammityskustannuksienEnimmaismaara(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getAsunto().isValmistunutTaiPerusparannettuEnnen1974()) {

				if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_1)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.70);
				} else if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_2)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.86);
				} else if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_3)) {
					hakemus.setLammityskustannuksienEnimmaismaara(3.06);
				}

			} else {

				if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_1)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.07);
				} else if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_2)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.20);
				} else if (hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_3)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.35);
				}

			}

		}
	}

	public void paatteleKohtuullinenAsunnonKoko(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.isHakijallaPuoliso()) {
				hakemus.setKohtuullinenAsunnonKoko(85);
			} else {
				hakemus.setKohtuullinenAsunnonKoko(70);
			}

		}
	}

	public void paatteleKunnossapidonKustannukset(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getAsunto().isValmistunutTaiPerusparannettuEnnen1974()) {
				hakemus.setKunnossapidonKustannukset(62.19);
			} else {
				hakemus.setKunnossapidonKustannukset(47.84);
			}
		}
	}

	public void paatteleKuntaryhma(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getAsunto().getSijaintikunta().equals(Kunta.HELSINKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ESPOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KAUNIAINEN)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.VANTAA)) {

				hakemus.setKuntaryhma(Kuntaryhma.KUNTARYHMA_1);

			} else if (hakemus.getAsunto().getSijaintikunta().equals(Kunta.HYVINKÄÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.HÄMEENLINNA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.JOENSUU)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.JYVÄSKYLÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.JÄRVENPÄÄ)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KERAVA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KIRKKONUMMI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KOUVOLA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KUOPIO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.LAHTI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.LAPPEENRANTA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.LOHJA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.NURMIJÄRVI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.OULU)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.PORI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.PORVOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.RAISIO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.RIIHIMÄKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ROVANIEMI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.SEINÄJOKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.SIPOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.TAMPERE)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.TURKU)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.TUUSULA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.VAASA)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.VIHTI)) {

				hakemus.setKuntaryhma(Kuntaryhma.KUNTARYHMA_2);

			} else {
				hakemus.setKuntaryhma(Kuntaryhma.KUNTARYHMA_3);
			}

		}
	}

	public void paatteleAsumismenojenEnimmaismaara(ElakkeensaajanasumistukiHakemus hakemus) {

		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {

			if (hakemus.getKuntaryhma().equals(Kuntaryhma.KUNTARYHMA_1)) {
				hakemus.setAsumismenojenEnimmaismaara(9287);

			} else if (hakemus.getKuntaryhma().equals(Kuntaryhma.KUNTARYHMA_2)) {
				hakemus.setAsumismenojenEnimmaismaara(8541);

			} else if (hakemus.getKuntaryhma().equals(Kuntaryhma.KUNTARYHMA_3)) {
				hakemus.setAsumismenojenEnimmaismaara(7493);
			}

		}
	}

}
