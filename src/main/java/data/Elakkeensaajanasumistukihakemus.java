package data;

import java.time.LocalDate;

import lombok.Data;

/**
 * Luokka sisältää laskennassa käytettävät lähtötiedot, vakiot ja laskennan aikana pääteltävät parametrit.
 *
 */
@Data
public class ElakkeensaajanasumistukiHakemus {
	
	// Hakemuksen jättöpäivä
	private LocalDate hakemuspaiva = LocalDate.now();
	
	// Laskennassa käytettävät lähtötiedot tulevat hakijalta
	private Hakija hakija;
	private Puoliso puoliso;
	private Asunto asunto;
	private boolean hakijallaPuoliso;
	
	// Laskennassa käytettävät vakiot
	private Vakiot vakiot;
	
	// Laskennan alussa pääteltävä tieto
	private boolean hakijallaOikeusAsumistukeen;
	
	// Asumismenojen liiketoimintasäännöt
	private double asumismenojenEnimmaismaara;
	private double kunnossapidonKustannukset;
	private int kohtuullinenAsunnonKoko;
	private LammitysRyhma lammitysRyhma;
	private Kuntaryhma kuntaryhma;
	private double lammityskustannuksienEnimmaismaara;
	
	// Asumismenojen laskentojen tulokset
	private double huomioonotettavaNeliomaara;
	private double lammityskustannukset;
	private double hoitomenot;
	private double asumismenotYhteensa;
	private double huomioonOtettavatAsumismenot;
	
	// Lisäomavastuun liiketoimintasäännöt
	private boolean puolisollaOikeusAsumistukeen;
	private double lisaomavastuunTuloraja;
	private double omaisuusRaja;
	
	// Lisäomavastuun laskentojen tulokset
	private double huomioonOtettavaOmaisuus;
	private double omaisuusrajanYlittavaOsuus;
	private double huomioonOtettavatTulot;
	private double tulorajanYlittavaOsuus;
	private double lisaomavastuunMaara;
	
	// Lopputulokseen vaikuttava liiketoimintasääntö
	private double pieninMaksettavaAsumistuki;
	
	// Hakemuksen valmistumista indikoiva muuttuja
	private boolean hakemusValmis = false;
	
	// Laskennan lopputulos
	private double elakkeensaajanasumistuenMaara;

}
