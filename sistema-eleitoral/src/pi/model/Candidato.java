package pi.model;

public class Candidato implements Elemento {

	private String nome;
	private Partido partido;
	private long qtdVotos;
	private short codigoCandidato;
	private static short codigoCandidatos = 0;
	private TipoCandidato tipoCandidato;

	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato) {
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		this.codigoCandidatos++;
		this.codigoCandidato = codigoCandidatos;

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

	@Override
	public String toString() {
		return "2;" + this.codigoCandidato + ";" + this.nome + ";" + this.partido.getCodigo();
	}

}
