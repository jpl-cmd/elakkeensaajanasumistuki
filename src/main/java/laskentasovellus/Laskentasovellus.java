package laskentasovellus;

import data.Asuntotyyppi;
import data.ElakkeensaajanasumistukiHakemus;
import data.ElakkeensaajanasumistukiRatkaisu;
import laskuri.Laskuri;

public class Laskentasovellus {
	
	ElakkeensaajanasumistukiHakemus hakemus;
	ElakkeensaajanasumistukiRatkaisu ratkaisu;
	PaattelyMoottori paattelyMoottori;
	Laskuri laskuri;
	
	
	
	// Laskentasovellus simuloi prosessia
	public void teeRatkaisu(ElakkeensaajanasumistukiRatkaisu ratkaisu) {
		
		this.ratkaisu = ratkaisu;
		this.hakemus = this.ratkaisu.getHakemus();
		this.paattelyMoottori = new PaattelyMoottori();
		this.laskuri = new Laskuri();
		
		
		// 1. Asetetaan hakemuksessa käytettävät vakiot
		paattelyMoottori.paatteleVakiot(hakemus);
		
		
		// 2. Tarkistetaan onko hakijalla oikeus asumistukeen
		paattelyMoottori.paatteleOnkoHakijallaOikeusAsumistukeen(hakemus);
		
		
		// Prosessin ensimmäinen haara
		if(hakemus.isHakijallaOikeusAsumistukeen()) {
			
			
			// Prosessin toinen haara
			if(hakemus.getAsunto().getAsuntotyyppi().equals(Asuntotyyppi.OMAKOTITALO)) {
				paattelyMoottori.paatteleKunnossapidonKustannukset(hakemus);
				paattelyMoottori.paatteleKohtuullinenAsunnonKoko(hakemus);
				paattelyMoottori.paatteleLammitysryhma(hakemus);
				paattelyMoottori.paatteleLammityskustannuksienEnimmaismaara(hakemus);
				laskuri.laskeHuomioonotettavaNeliomaara(hakemus);
				laskuri.laskeLammityskustannukset(hakemus);
				laskuri.laskeHoitomenot(hakemus);
			}
			
			paattelyMoottori.paatteleKuntaryhma(hakemus);
			paattelyMoottori.paatteleAsumismenojenEnimmaismaara(hakemus);
			laskuri.laskeAsumismenotYhteensa(hakemus);
			laskuri.laskeHuomioonotettavatAsumismenot(hakemus);
			
			// Prosessin kolmas haara
			if(hakemus.isHakijallaPuoliso()) {
				paattelyMoottori.paatteleOnkoPuolisollaOikeusAsumistukeen(hakemus);
			}
			
			paattelyMoottori.paatteleLisaomavastuunTuloraja(hakemus);
			paattelyMoottori.paatteleOmaisuusRaja(hakemus);
			laskuri.laskeHuomioonotettavaOmaisuus(hakemus);
			laskuri.laskeOmaisuusrajanYlittavaOsuus(hakemus);
			laskuri.laskeHuomioonotettavatTulot(hakemus);
			laskuri.laskeTulorajanYlittavaOsuus(hakemus);
			laskuri.laskeLisaomavastuu(hakemus);
			laskuri.laskeElakkaansaajanasumistuki(hakemus);
			paattelyMoottori.paattelePieninMaksettavaAsumistuki(hakemus);
			laskuri.laskeLopputulos(ratkaisu);
			
		}
		
	}
}





