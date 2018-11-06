package pi.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;
import pi.model.cpf.CPF;

public class Teste {
	public static void main(String[] args) {
		
		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();

		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);

		Eleitor el1 = new Eleitor(UF.MA, 1, 0, cand1, cand2, cand3);
		Eleitor el2 = new Eleitor(UF.MA, 2, 123, cand1, cand2, cand3);
		Eleitor el3 = new Eleitor(UF.MA, 3, 123, cand1, cand2, cand3);
		Eleitor el4 = new Eleitor(UF.MA, 4, 123, cand1, cand2, cand3);
		Eleitor el5 = new Eleitor(UF.MA, -100, 0, cand1, cand2, cand3);
		Eleitor el6 = new Eleitor(UF.MA, -10, 0, cand1, cand2, cand3);
		Eleitor el7 = new Eleitor(UF.MA, -5, 0, cand1, cand2, cand3);
		Eleitor el8 = new Eleitor(UF.MA, -2, 0, cand1, cand2, cand3);
		Eleitor el9 = new Eleitor(UF.MA, -7, 0, cand1, cand2, cand3);
		Eleitor el10 = new Eleitor(UF.MA, -9, 0, cand1, cand2, cand3);
		Eleitor el11 = new Eleitor(UF.MA, -100, 0, cand1, cand2, cand3);

		
		
		
		

		ab.add(el1);
		ab.add(el2);
		ab.add(el3);
		ab.add(el4);
		ab.add(el5);		
		ab.add(el6);
		ab.add(el7);
		ab.add(el8);
		ab.add(el9);
		ab.add(el10);
		ab.remove(1);
		ab.emOrdem();
		
		System.out.println("\n\n\n" + ab.getRaiz().getEsquerda().getPai());
		System.out.println(ab.getRaiz());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
