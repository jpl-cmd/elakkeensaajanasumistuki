package data;


import java.util.ArrayList;

import lombok.Data;

@Data
public class Henkilo {
	
	private int ika;
	private double tulot;
	private double omaisuus;
	private double velat;
	private Etuus etuus;

}
