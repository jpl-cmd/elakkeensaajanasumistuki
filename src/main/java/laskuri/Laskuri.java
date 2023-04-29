package laskuri;

import data.Asuntotyyppi;
import data.ElakkeensaajanasumistukiHakemus;
import data.ElakkeensaajanasumistukiRatkaisu;
import data.Vakiot;

public class Laskuri {

	/*
	 * LISÄOMAVASTUUSEEN LIITTYVÄT LASKUT
	 */

	public void laskeHuomioonotettavaOmaisuus(ElakkeensaajanasumistukiHakemus hakemus) {
		if (hakemus.isHakijallaPuoliso()) {
			hakemus.setHuomioonOtettavaOmaisuus((hakemus.getHakija().getOmaisuus() - hakemus.getHakija().getVelat())
					+ (hakemus.getPuoliso().getOmaisuus() - hakemus.getPuoliso().getVelat()));
		} else {
			hakemus.setHuomioonOtettavaOmaisuus(hakemus.getHakija().getOmaisuus() - hakemus.getHakija().getVelat());
		}
		
		if(hakemus.getHuomioonOtettavaOmaisuus() < 0) {
			hakemus.setHuomioonOtettavaOmaisuus(0);
		}
	}

	public void laskeOmaisuusrajanYlittavaOsuus(ElakkeensaajanasumistukiHakemus hakemus) {
		double tulos = hakemus.getHuomioonOtettavaOmaisuus() - hakemus.getOmaisuusRaja();

		if (tulos > 0) {
			hakemus.setOmaisuusrajanYlittavaOsuus(tulos);
		} else {
			hakemus.setOmaisuusrajanYlittavaOsuus(0);
		}
	}

	public void laskeHuomioonotettavatTulot(ElakkeensaajanasumistukiHakemus hakemus) {
		if (hakemus.isHakijallaPuoliso()) {
			hakemus.setHuomioonOtettavatTulot(hakemus.getHakija().getTulot() + hakemus.getPuoliso().getTulot()
					+ (hakemus.getVakiot().getOmaisuusKerroin() * hakemus.getOmaisuusrajanYlittavaOsuus()));
		} else {
			hakemus.setHuomioonOtettavatTulot(hakemus.getHakija().getTulot()
					+ (hakemus.getVakiot().getOmaisuusKerroin() * hakemus.getOmaisuusrajanYlittavaOsuus()));
		}

	}

	public void laskeTulorajanYlittavaOsuus(ElakkeensaajanasumistukiHakemus hakemus) {
		double tulos = hakemus.getHuomioonOtettavatTulot() - (hakemus.getLisaomavastuunTuloraja() / 12);
		
		if (tulos > 0) {
			hakemus.setTulorajanYlittavaOsuus(tulos);
		} else {
			hakemus.setTulorajanYlittavaOsuus(0);
		}
	}

	public void laskeLisaomavastuu(ElakkeensaajanasumistukiHakemus hakemus) {
		hakemus.setLisaomavastuunMaara(
				hakemus.getVakiot().getLisaomavastuuKerroin() * hakemus.getTulorajanYlittavaOsuus());
	}

	/*
	 * HUOMIOONOTETTAVIIN ASUMISMENOIHIN LIITTYVÄT LASKUT
	 */

	public void laskeHuomioonotettavaNeliomaara(ElakkeensaajanasumistukiHakemus hakemus) {
		if (hakemus.getAsunto().getPintaAla() > hakemus.getKohtuullinenAsunnonKoko()) {
			hakemus.setHuomioonotettavaNeliomaara(hakemus.getKohtuullinenAsunnonKoko());
		} else {
			hakemus.setHuomioonotettavaNeliomaara(hakemus.getAsunto().getPintaAla());
		}
	}

	public void laskeLammityskustannukset(ElakkeensaajanasumistukiHakemus hakemus) {
		hakemus.setLammityskustannukset(
				hakemus.getHuomioonotettavaNeliomaara() * hakemus.getLammityskustannuksienEnimmaismaara());
	}

	public void laskeHoitomenot(ElakkeensaajanasumistukiHakemus hakemus) {
		hakemus.setHoitomenot(hakemus.getKunnossapidonKustannukset() + hakemus.getLammityskustannukset()
				+ hakemus.getVakiot().getOmakotitalonVesimaksunEnimmaismaara());
	}

	public void laskeAsumismenotYhteensa(ElakkeensaajanasumistukiHakemus hakemus) {
		if (hakemus.getAsunto().getAsuntotyyppi().equals(Asuntotyyppi.OMAKOTITALO)) {
			hakemus.setAsumismenotYhteensa(hakemus.getHoitomenot() + hakemus.getAsunto().getAsumismenot());
		} else {
			hakemus.setAsumismenotYhteensa(hakemus.getAsunto().getAsumismenot());
		}
	}

	public void laskeHuomioonotettavatAsumismenot(ElakkeensaajanasumistukiHakemus hakemus) {
		double asumismenotEnimmaismaaraKuukaudessa = hakemus.getAsumismenojenEnimmaismaara() / 12;
		if (hakemus.getAsumismenotYhteensa() > asumismenotEnimmaismaaraKuukaudessa) {
			hakemus.setHuomioonOtettavatAsumismenot(hakemus.getAsumismenojenEnimmaismaara());
		} else {
			hakemus.setHuomioonOtettavatAsumismenot(hakemus.getAsumismenotYhteensa());
		}
	}

	public void laskeElakkaansaajanasumistuki(ElakkeensaajanasumistukiHakemus hakemus) {
		Vakiot vakiot = hakemus.getVakiot();
		double maara = vakiot.getElakkeensaajanasumistukiKerroin() * (hakemus.getHuomioonOtettavatAsumismenot()
				- (vakiot.getPerusomavastuu() + hakemus.getLisaomavastuunMaara()));

		hakemus.setElakkeensaajanasumistuenMaara(maara);
	}

	/*
	 * LASKENNAN LOPPUTULOKSEN LASKEMINEN JA ASETTAMINEN RATKAISUUN
	 */
	public void laskeLopputulos(ElakkeensaajanasumistukiRatkaisu ratkaisu) {
		double asumistuenmaara = ratkaisu.getHakemus().getElakkeensaajanasumistuenMaara();
		if (ratkaisu.getHakemus().isHakijallaOikeusAsumistukeen()) {
			
			if(ratkaisu.getHakemus().isHakijallaPuoliso() && ratkaisu.getHakemus().isPuolisollaOikeusAsumistukeen()) {
				asumistuenmaara /= 2;
			}
			
			if(asumistuenmaara >= ratkaisu.getHakemus().getPieninMaksettavaAsumistuki()) {
				ratkaisu.setTukiMyonnetty(true);
				ratkaisu.setMyonnetynTuenMaara(Math.floor(asumistuenmaara * 100) / 100);
			}else {
				ratkaisu.setTukiMyonnetty(false);
				ratkaisu.setMyonnetynTuenMaara(0);
			}
			
		} else {
			ratkaisu.setTukiMyonnetty(false);
			ratkaisu.setMyonnetynTuenMaara(0);
			
		}

	}

}
