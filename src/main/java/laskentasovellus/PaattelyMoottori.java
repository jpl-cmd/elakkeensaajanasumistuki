package laskentasovellus;

import java.time.LocalDate;

import data.ElakkeensaajanasumistukiHakemus;
import data.Kunta;
import data.Kuntaryhma;
import data.LammitysRyhma;
import util.PvmTarkistus;

public class PaattelyMoottori {

	LocalDate alkupvm = LocalDate.parse("2023-01-01");
	LocalDate loppupvm = LocalDate.parse("2023-12-31");

//	if(PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
//		
//	}

	
	
	
	
	public void paatteleLammitysryhma(ElakkeensaajanasumistukiHakemus hakemus) {
		
		if(PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
			
			
			if(hakemus.getAsunto().getSijaintikunta().equals(Kunta.ASKOLA)) {
				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_1);
				
				
			}else if(hakemus.getAsunto().getSijaintikunta().equals(Kunta.AKAA)) {
				
				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_2);
				
			}else {
				
				hakemus.setLammitysRyhma(LammitysRyhma.LAMMITYSRYHMA_3);
				
			}
			
			
		}
		
	}
	
	
	
	
	public void paatteleLammityskustannuksienEnimmaismaara(ElakkeensaajanasumistukiHakemus hakemus) {
		
		if(PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
			
			if(hakemus.getAsunto().isValmistunutTaiPerusparannettuEnnen1974()) {
				
				if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_1)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.70);
				}else if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_2)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.86);
				}else if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_3)){
					hakemus.setLammityskustannuksienEnimmaismaara(3.06);
				}
				
			}else {
				
				if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_1)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.07);
				}else if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_2)) {
					hakemus.setLammityskustannuksienEnimmaismaara(2.20);
				}else if(hakemus.getLammitysRyhma().equals(LammitysRyhma.LAMMITYSRYHMA_3)){
					hakemus.setLammityskustannuksienEnimmaismaara(2.35);
				}
				
			}
			
		}
	}
	
	
	
	public void paatteleKohtuullinenAsunnonKoko(ElakkeensaajanasumistukiHakemus hakemus) {
		
		if(PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
			
			if(hakemus.isHakijallaPuoliso()){
				hakemus.setKohtuullinenAsunnonKoko(85);
			}else {
				hakemus.setKohtuullinenAsunnonKoko(70);
			}
		
		}
	}
	
	
	public void paatteleKunnossapidonKustannukset(ElakkeensaajanasumistukiHakemus hakemus) {
		
		if(PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
			
			if(hakemus.getAsunto().isValmistunutTaiPerusparannettuEnnen1974()) {
				hakemus.setKunnossapidonKustannukset(62.19);
			}else {
				hakemus.setKunnossapidonKustannukset(47.84);
			}
		}
	}
	
	
	public void paatteleKuntaryhma(ElakkeensaajanasumistukiHakemus hakemus) {
		
		if (PvmTarkistus.sisaltyyAjanjaksoon(hakemus.getHakemuspaiva(), alkupvm, loppupvm)) {
			
			if(hakemus.getAsunto().getSijaintikunta().equals(Kunta.HELSINKI)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.ESPOO)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.KAUNIAINEN)
					|| hakemus.getAsunto().getSijaintikunta().equals(Kunta.VANTAA)) {
				
				hakemus.setKuntaryhma(Kuntaryhma.KUNTARYHMA_1);
				
				
			}else if(hakemus.getAsunto().getSijaintikunta().equals(Kunta.HYVINKÄÄ)
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
				
				
			}else {
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
