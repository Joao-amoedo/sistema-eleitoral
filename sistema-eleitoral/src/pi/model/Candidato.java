package pi.model;

public class Candidato implements Elemento {

	private String nome;
	private Partido partido;
	private long qtdVotos;
	private TipoCandidato tipoCandidato;
	private long codigoCandidato;
	private static long codigoCandidatos = 0;
	private static long codigoCandidatoFederal = 0;
	private static long codigoCandidatoRegional = 10000;
	private static long codigoCandidatoPresidente = 1000;

	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato) {
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		codigoCandidato();
		this.codigoCandidatos++;
	}
	
	private void codigoCandidato() {
		if(this.tipoCandidato == TipoCandidato.FEDERAL) {
			this.codigoCandidato = codigoCandidatoFederal;
			codigoCandidatoFederal++;
		}
		else if(this.tipoCandidato == TipoCandidato.REGIONAL) {
			this.codigoCandidato = codigoCandidatoRegional;
			codigoCandidatoRegional++;
		}
		if(this.tipoCandidato == TipoCandidato.PRESIDENCIAVEL) {
			this.codigoCandidato = codigoCandidatoPresidente;
			codigoCandidatoPresidente++;
		}
	}

	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato, short codigoCandidato) {
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		this.codigoCandidato = codigoCandidato;
		this.codigoCandidatos++;
	}

	public String getNome() {
		return nome;
	}

	public Partido getPartido() {
		return partido;
	}

	public long getQtdVotos() {
		return qtdVotos;
	}

	public long getElemento() {
		return codigoCandidato;
	}

	public TipoCandidato getTipoCandidato() {
		return tipoCandidato;
	}

	public void acressentaVoto() {
		this.qtdVotos++;
	}
	
	public static long getCodigoCandidatos() {
		return Candidato.codigoCandidatos;
	}
	
	@Override
	public String toString() {
		return "2;" + this.codigoCandidato + ";" + this.nome + ";" + this.partido.getCodigo();
	}

}
