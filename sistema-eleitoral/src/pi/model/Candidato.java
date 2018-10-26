package pi.model;
public class Candidato {

	private String nome;
	private Partido partido;
	private long qtdVotos;
	private short codigoCandidato;
	private static short codigoCandidatos;
	private TipoCandidato tipoCandidato;
	
	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato){
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		codigoCandidatos++;
		this.codigoCandidato = codigoCandidatos;
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

	public short getCodigoCandidato() {
		return codigoCandidato;
	}

	public TipoCandidato getTipoCandidato() {
		return tipoCandidato;
	}
	
	
	@Override
	public String toString() {
		return "2;" + this.codigoCandidato + ";" + this.nome + ";" + this.partido.getCodigo();
	}
	
}
