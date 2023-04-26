package data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Hakija extends Henkilo{
	
	private Puoliso puoliso;
	private Asunto asunto;

}
