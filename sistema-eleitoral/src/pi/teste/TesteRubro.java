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

		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);
		
		Eleitor el1 = new Eleitor(UF.MA, 10 ,0,cand1,cand2,cand3);
		Eleitor el2 = new Eleitor(UF.MA, 9 ,0,cand1,cand2,cand3);
		Eleitor el3 = new Eleitor(UF.MA, 3 ,0,cand1,cand2,cand3);
		Eleitor el4 = new Eleitor(UF.MA, 4 ,0,cand1,cand2,cand3);

		ab.add(el1);
		ab.add(el2);
		ab.add(el3);
		
		System.out.println(ab.raiz.getElemento());
	
		
	}
}
