package pi.arvore;

import java.util.Random;

import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class TesteRubro {
	public static void main(String[] args) {
		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();

		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);

		Eleitor el1 = new Eleitor(UF.MA, 1, 0, cand1, cand2, cand3);
		Eleitor el2 = new Eleitor(UF.MA, 2, 0, cand1, cand2, cand3);
		Eleitor el3 = new Eleitor(UF.MA, 3, 0, cand1, cand2, cand3);
		Eleitor el4 = new Eleitor(UF.MA, 4, 0, cand1, cand2, cand3);
		Random rand = new Random();
		int qtd = 150;
		for (int x = 1; x <= qtd; x++) {
			ab.add(new Eleitor(UF.MA, rand.nextLong(), 0, cand1, cand2, cand3));
		}

		ab.emOrdem();
		System.out.println(ab.toList().size());
//		System.out.println("raiz " + ab.raiz.getElemento());
//		System.out.println(ab.raiz.direita.getElemento());
//		System.out.println(ab.raiz.esquerda.getElemento());
//		

	}
}
