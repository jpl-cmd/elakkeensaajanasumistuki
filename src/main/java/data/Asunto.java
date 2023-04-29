package data;

import lombok.Data;

@Data
public class Asunto {
	
	private Kunta sijaintikunta;
	private Asuntotyyppi asuntotyyppi;
	private double asumismenot;
	
	// Koskevat vain omakotitaloa
	private int pintaAla;
	private boolean valmistunutTaiPerusparannettuEnnen1974;

}
