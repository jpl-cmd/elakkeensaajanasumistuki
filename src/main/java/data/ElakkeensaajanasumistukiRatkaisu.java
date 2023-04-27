package data;

import lombok.Data;

@Data
public class ElakkeensaajanasumistukiRatkaisu {
	
	ElakkeensaajanasumistukiHakemus hakemus;
	
	private boolean tukiMyonnetty;
	private double myonnetynTuenMaara;

}
