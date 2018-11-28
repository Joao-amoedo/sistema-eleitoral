package pi.model;

import java.util.Arrays;
import java.util.Random;

public enum TipoCandidato {
	FEDERAL((short) 0),REGIONAL((short)1);
	
	private short codigo;
	
	TipoCandidato(short codigo){
		this.codigo = codigo;
	}
	
	public short getCodigo() {
		return this.codigo;
	}
	
	public static TipoCandidato getTipoCandidato(short codigo) {
		return Arrays.asList(TipoCandidato.values())
				.stream()
				.filter(tc -> tc.getCodigo() == codigo)
				.findFirst()
				.get();
	}
	
	
	
	
}
