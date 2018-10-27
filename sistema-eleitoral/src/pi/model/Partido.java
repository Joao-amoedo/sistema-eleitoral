package pi.model;

public enum Partido {
	
	PSOL(1),PMDB(2),PT(3),NOVO(30);

	private int codigo;

	Partido(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}
	
	public static Partido getPartido(int codigo) {
		Partido[] values = Partido.values();
		for (Partido partido : values) {
			
			if(partido.getCodigo() == codigo)
				return partido;
		}
		return null;
	}
	
}
