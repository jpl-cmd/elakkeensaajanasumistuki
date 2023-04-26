package data;

import lombok.Data;

/**
 * Luokka sisältää laskennassa käytettävät lähtötiedot, vakiot ja laskennan aikana pääteltävät parametrit.
 *
 */
@Data
public class Elakkeensaajanasumistukihakemus {
	
	// Laskennassa käytettävät lähtötiedot tulevat hakijalta
	private Hakija hakija;
	
	// Laskennassa käytettävät vakiot
	private Vakiot vakiot;
	
	// Laskennassa pääteltävät tiedot
	private boolean hakijallaPuoliso = false;
	
	private boolean hakijallaOikeusAsumistukeen = false;
	private boolean puolisollaOikeusAsumistukeen = false;
	
	private double huomioonOtettavatAsumismenot = 0.0;
	private double lisaomavastuunTuloraja = 0.0;
	private double lisaomavastuunMaara = 0.0;
	private double omaisuusRaja = 0.0;
	private double pieninMaksettavaAsumistuki = 0.0;
	
	private boolean hakemusValmis = false;
	private boolean hakemusHyvaksytty = false;
	private double elakkeensaajanasumistuenMaara;
	
	

}
