package pi.teste;

import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class TesteRubro {
	public static void main(String[] args) {

		ArvoreAVL<Eleitor> ab = new ArvoreAVL<Eleitor>();
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);

		Eleitor el1 = new Eleitor(UF.MA, 10, 0, cand1, cand2, cand3);
		Eleitor el2 = new Eleitor(UF.MA, 20, 0, cand1, cand2, cand3);
		Eleitor el3 = new Eleitor(UF.MA, 5, 0, cand1, cand2, cand3);
		Eleitor el4 = new Eleitor(UF.MA, 3, 0, cand1, cand2, cand3);
		Eleitor el5 = new Eleitor(UF.MA, 30, 0, cand1, cand2, cand3);
		Eleitor el6 = new Eleitor(UF.MA, 15, 0, cand1, cand2, cand3);
		Eleitor el7 = new Eleitor(UF.MA, 7, 0, cand1, cand2, cand3);

		ab.add(el1);
		ab.add(el2);
		ab.add(el3);
		ab.add(el4);
		ab.add(el5);
		ab.add(el6);
		ab.add(el7);

		Eleitor busca = ab.buscaProfundidade(-1);

		System.out.println(busca == null);

//		System.out.println(ab.raiz.getElemento());

	}
}
