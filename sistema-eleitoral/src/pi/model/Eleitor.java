package pi.model;

public class Eleitor implements Elemento{

	private long cpf;
	private UF regiao;
	private int codigoMunicipio;
	private Candidato candidatoFederal;
	private Candidato candidatoRegional;
	private long sequencial;
	private static long sequencialEleitores;

	public Eleitor(UF regiao,Integer cpf,  int codigoMunicipio, Candidato candidatoFederal,
			Candidato candidatoRegional) {
		this.cpf = cpf;
		this.regiao = regiao;
		this.codigoMunicipio = codigoMunicipio;
		this.candidatoFederal = candidatoFederal;
		this.candidatoRegional = candidatoRegional;
		this.sequencialEleitores++;
		this.sequencial = sequencialEleitores;
	}
	
	public Eleitor(UF regiao,Integer cpf, long sequencial,  int codigoMunicipio, Candidato candidatoFederal,
			Candidato candidatoRegional) {
		this.cpf = cpf;
		this.regiao = regiao;
		this.codigoMunicipio = codigoMunicipio;
		this.candidatoFederal = candidatoFederal;
		this.candidatoRegional = candidatoRegional;
		this.sequencialEleitores++;
		this.sequencial = sequencial;
	}

	public long getElemento() {
		return cpf;
	}

	public long getSequencial() {
		return sequencial;
	}

	public UF getRegiao() {
		return regiao;
	}

	public int getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public int getCodigoPartidoFederal() {
		return candidatoFederal.getPartido().getCodigo();
	}

	public int getCodigoPartidoRegional() {
		return candidatoRegional.getPartido().getCodigo();
	}

	public static long getSequencialEleitores() {
		return sequencialEleitores;
	}

	@Override
	public String toString() {
		return "2;" + regiao + ";" + cpf + ";" + sequencial + ";" + codigoMunicipio + ";"
				+ candidatoFederal.getElemento() + ";" + 
				this.getCodigoPartidoFederal() + ";"
				+ candidatoRegional.getElemento() + ";" + 
				this.getCodigoPartidoRegional();
	}

}
