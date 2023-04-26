package laskentasovellus;

import data.Elakkeensaajanasumistukihakemus;
import data.Etuus;
import data.Hakija;
import data.Vakiot;

public class Laskentasovellus {
	
	Elakkeensaajanasumistukihakemus hakemus;
	
	
	
	public void kasitteleHakemus(Elakkeensaajanasumistukihakemus hakemus) {
		
		this.hakemus = hakemus;
		
		// Asetetaan hakemuksessa käytettävät vakiot
		asetaVakiot();
		
		// Prosessissa asetetaan laskennassa vaadittavat muuttujat oikeisiin arvoihin
	
		// Varsinainen laskenta tapahtuu tämän jälkeen
		
		
		
		
		
		
		// Lasketaan tulokset
		Laskuri laskuri = new Laskuri();
		laskuri.laskeElakkaansaajanasumistuki(hakemus);
		
		
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
