package pi.teste;

import pi.arvore.ArvoreBinaria;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class TesteAdd {
	public static void main(String[] args) {

		Candidato caFed = new Candidato("Adamastor Pequeno", Partido.NOVO, TipoCandidato.FEDERAL);
		Candidato caReg = new Candidato("Adamastor Pequeno", Partido.PSOL, TipoCandidato.REGIONAL);
		Candidato caPres = new Candidato("Adamastor Maior", Partido.PSOL, TipoCandidato.PRESIDENTE);
		Eleitor el1 = new Eleitor(UF.PA, 1, 66, caFed, caReg,caPres);

		ArvoreBinaria<Eleitor> ab = new ArvoreBinaria<Eleitor>();
		System.out.println(ab.add(el1));
		System.out.println(ab.remove(2));
		ab.emOrdem();
	}
}
