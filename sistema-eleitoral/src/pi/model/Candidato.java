package pi.model;

public class Candidato implements Elemento {

	private String nome;
	private Partido partido;
	private long qtdVotos;
	private TipoCandidato tipoCandidato;
	private long codigoCandidato;
	private static long codigoCandidatos = 0;
	private static long codigoCandidatoRegional = 10000;

	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato) {
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		codigoCandidato = codigoCandidatoRegional;
		codigoCandidatoRegional++;
		this.codigoCandidatos++;
	}


	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato, short codigoCandidato) {
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		this.codigoCandidato = codigoCandidato;
		if(tipoCandidato.equals(TipoCandidato.REGIONAL))
			codigoCandidatoRegional++;
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
