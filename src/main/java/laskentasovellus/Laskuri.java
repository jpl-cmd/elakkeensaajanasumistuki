package laskentasovellus;

import data.Elakkeensaajanasumistukihakemus;
import data.Vakiot;

public class Laskuri {
	
	
	public void laskeElakkaansaajanasumistuki(Elakkeensaajanasumistukihakemus hakemus) {
		Vakiot vakiot = hakemus.getVakiot();
		
		double maara = vakiot.getElakkeensaajanasumistukiKerroin() 
				* ( hakemus.getHuomioonOtettavatAsumismenot() 
				- (vakiot.getPerusomavastuu() + hakemus.getLisaomavastuunMaara()));
		
		hakemus.setElakkeensaajanasumistuenMaara(maara);
		
	}
	
	
	

}
