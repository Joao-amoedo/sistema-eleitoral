package pi.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Partido {

	MDB(1), PTB(2), PDT(3), PT(4), DEM(5), PCdoB(6), PSB(7), PSDB(8), PTC(9), PSC(10), PMN(11), PRP(12), PPS(13),
	PV(14), AVANTE(15), PP(16), PSTU(17), PCB(18), PRTB(19), PHS(20), DC(21), PCO(22), PODE(23), PSL(24), PRB(25),
	PSOL(26), PR(27), PSD(28), PPL(29), PATRI(30), PROS(31), SOLIDARIEDADE(32), NOVO(33), REDE(34), PMB(35);

	private int codigo;

	Partido(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public static Partido getPartido(int codigo) {
		if(codigo > 35 || codigo < 1)
			return null;
		else return Arrays.asList(Partido.values()).stream().filter(tc -> tc.getCodigo() == codigo).findFirst().get();
	}

	public static Partido getPartidoAleatorio() {
		Random rand = new Random();
		List<Partido> listPartido = Arrays.asList(Partido.values());
		return listPartido.get(rand.nextInt(listPartido.size()));
	}

	public String toString() {
		return this.codigo + " - " + this.name();
	}

}
