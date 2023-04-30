package util;

import java.time.LocalDate;

public class Util {

	public static boolean sisaltyyAjanjaksoon(LocalDate pvm, LocalDate alkupvm, LocalDate loppuPvm) {
		if ( (pvm.equals(alkupvm) || pvm.isAfter(alkupvm)) && (pvm.equals(loppuPvm) || pvm.isBefore(loppuPvm))) {
			return true;
		}
		
		return false;

	}

}
