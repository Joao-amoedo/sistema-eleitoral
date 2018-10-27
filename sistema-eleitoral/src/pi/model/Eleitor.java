package pi.model;

public class Eleitor implements Elemento{

	private long cpf;
	private UF regiao;
	private int codigoMunicipio;
	private Candidato candidatoFederal;
	private Candidato candidatoRegional;
	private long sequencial;
	private static long sequencialEleitores;

	public Eleitor(UF regiao,long cpf,  int codigoMunicipio, Candidato candidatoFederal,
			Candidato candidatoRegional) {
		this.cpf = cpf;
		this.regiao = regiao;
		this.codigoMunicipio = codigoMunicipio;
		this.candidatoFederal = candidatoFederal;
		this.candidatoRegional = candidatoRegional;
		this.sequencialEleitores++;
		this.sequencial = sequencialEleitores;
	}
	
	public Eleitor(UF regiao,long cpf, long sequencial,  int codigoMunicipio, Candidato candidatoFederal,
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
		return "2;" + regiao + ";" + mascaraCPF() + ";" + sequencial + ";" + codigoMunicipio + ";"
				+ candidatoFederal.getElemento() + ";" + 
				this.getCodigoPartidoFederal() + ";"
				+ candidatoRegional.getElemento() + ";" + 
				this.getCodigoPartidoRegional();
	}
	
	private String mascaraCPF() {
		String cpfString = String.valueOf(this.cpf);
		int contador = 0;

		if(cpfString.length() < 11) {
			contador = 11 - cpfString.length();
		}
		
		while(contador > 0) {
			cpfString = "0" + cpfString;
			contador--;			
		}
		String substring1 = cpfString.substring(0, 3);
		String substring2 = cpfString.substring(3,6);
		String substring3 = cpfString.substring(6,9);
		String substring4 = cpfString.substring(9,11);
		String format = String.format("%s.%s.%s-%s",substring1,substring2,substring3,substring4);	
		
		
		return format;
	}

}
