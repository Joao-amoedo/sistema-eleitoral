package pi.model;
public class Candidato implements Elemento{

	
	private String nome;
	private Partido partido;
	private long qtdVotos;
	private short codigoCandidato;
	private static short codigoCandidatosRegionais = 1000;
	private static short codigoCandidatosFederais = 0;
	private TipoCandidato tipoCandidato;
	
	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato){
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		if(tipoCandidato == TipoCandidato.REGIONAL) {
			
			codigoCandidatosRegionais++;
			codigoCandidato = codigoCandidatosRegionais;
		}
		else {
			codigoCandidatosFederais++;
			this.codigoCandidato = codigoCandidatosFederais;
		}
	}
	public Candidato(String nome, Partido partido, TipoCandidato tipoCandidato,short codigoCandidato){
		this.nome = nome;
		this.partido = partido;
		this.tipoCandidato = tipoCandidato;
		codigoCandidatosRegionais++;
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
