package pi.teste;

import java.util.ArrayList;
import java.util.List;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class Teste2 {

	public static void main(String[] args) {

		List<Eleitor> list = new ArrayList<Eleitor>();
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);
		Eleitor el1 = new Eleitor(UF.MA, 1, 0, cand1, cand2, cand3);
		Eleitor el2 = new Eleitor(UF.MA, 2, 0, cand1, cand2, cand3);
		Eleitor el3 = new Eleitor(UF.MA, 3, 0, cand1, cand2, cand3);

		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();

		ab.add(el1);
		ab.add(el2);
		ab.add(el3);
		
//		ab.emOrdem();
		
		ab.remove(3);
		System.out.println();
		ab.emOrdem();
		
		
	}

}
