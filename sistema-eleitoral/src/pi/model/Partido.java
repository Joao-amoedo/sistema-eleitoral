package pi.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
		return Arrays.asList(Partido.values())
				.stream()
				.filter(tc -> tc.getCodigo() == codigo)
				.findFirst()
				.get();
	}
	
	public static Partido getPartidoAleatorio() {
		Random rand = new Random();
		List<Partido> listPartido = Arrays.asList(Partido.values());
		return listPartido.get(rand.nextInt(listPartido.size()));

	}
	
}
