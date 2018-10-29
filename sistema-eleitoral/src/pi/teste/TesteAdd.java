package pi.teste;

import pi.arvore.ArvoreBinaria;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class TesteAdd {
	public static void main(String[] args) {
		
		
		Candidato caFed = new Candidato("Adamastor Pequeno",
				Partido.NOVO, TipoCandidato.FEDERAL);
		Candidato caReg = new Candidato("Adamastor Pequeno",
				Partido.PSOL, TipoCandidato.REGIONAL);
		Eleitor el1 = new Eleitor( UF.PA, 1, 66, caFed, caReg);
		Eleitor el2 = new Eleitor( UF.PA, 2, 66, caFed, caReg);
		Eleitor el3 = new Eleitor( UF.PA, 3, 66, caFed, caReg);
		Eleitor el4 = new Eleitor( UF.PA, 3, 66, caFed, caReg);
		
		ArvoreBinaria<Eleitor> ab = new ArvoreBinaria<Eleitor>();
		System.out.println(ab.add(el1));
		System.out.println(ab.add(el2));
		System.out.println(ab.add(el3));
		System.out.println(ab.add(el4));
		System.out.println(ab.remove(2));
		ab.emOrdem();
	}
}
