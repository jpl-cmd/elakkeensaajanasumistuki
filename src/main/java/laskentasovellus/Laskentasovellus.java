package laskentasovellus;

import data.ElakkeensaajanasumistukiHakemus;
import data.ElakkeensaajanasumistukiRatkaisu;
import data.Etuus;
import data.Hakija;
import data.Vakiot;
import laskuri.Laskuri;

public class Laskentasovellus {
	
	ElakkeensaajanasumistukiHakemus hakemus;
	ElakkeensaajanasumistukiRatkaisu ratkaisu;
	
	
	
	// Metodi simuloi prosessia
	public void teeRatkaisu(ElakkeensaajanasumistukiRatkaisu ratkaisu) {
		
		this.ratkaisu = ratkaisu;
		this.hakemus = this.ratkaisu.getHakemus();
		
		
		// Asetetaan hakemuksessa käytettävät vakiot
		asetaVakiot();
		
		
		
		
		
		
		
		// Lasketaan tulokset
		Laskuri laskuri = new Laskuri();
		laskuri.laskeElakkaansaajanasumistuki(ratkaisu);
		
		
	}


	private void asetaVakiot() {
		Vakiot vakiot = hakemus.getVakiot();
		
		vakiot.setElakkeensaajanasumistukiKerroin(0.85);
		vakiot.setPerusomavastuu(56.78);
		vakiot.setLisaomavastuuKerroin(0.413);
		vakiot.setOmaisuusKerroin(0.8);
		
		
	}
	
	private void asetaLisaomavastuunTuloraja() {
		
	}
	
	private void asetaOmaisuusRaja() {
		if(hakemus.isHakijallaPuoliso()) {
			hakemus.setOmaisuusRaja(29290);
		}else {
			hakemus.setOmaisuusRaja(18306);
		}
	}
		
	
	


}
