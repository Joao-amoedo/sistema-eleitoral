package pi.model;
public class Candidato implements Elemento{

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
		if(tipoCandidato == TipoCandidato.REGIONAL)
			this.codigoCandidato = (short) (codigoCandidatos + 1000);
		else
			this.codigoCandidato = codigoCandidatos;
	}
	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato,short codigoCandidato){
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		codigoCandidatos++;
		this.codigoCandidato = codigoCandidato;
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
	
	
	@Override
	public String toString() {
		return "2;" + this.codigoCandidato + ";" + this.nome + ";" + this.partido.getCodigo();
	}
	
}
