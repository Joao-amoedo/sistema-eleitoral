package pi.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreBinaria;
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
		Eleitor el1 = new Eleitor(UF.MA, 10, 0, cand1, cand2, cand3);
		Eleitor el2 = new Eleitor(UF.MA, 5, 0, cand1, cand2, cand3);
		Eleitor el3 = new Eleitor(UF.MA, 7, 0, cand1, cand2, cand3);
		Eleitor el4 = new Eleitor(UF.MA, 1, 0, cand1, cand2, cand3);
		
		ab.add(el1);
		ab.add(el2);
		ab.add(el3);
		System.out.println(ab.raiz.esquerda.getPai().getElemento());
//		ab.add(el4);
//		System.out.println(ab.raiz.getElemento());
//		Random rand = new Random();
//		long qtd = 3000000;
//		List<Integer> list = new ArrayList<Integer>();
//		for(int x = 0; x < qtd; x++)
//			list.add(rand.nextInt());
//		
//		
//		long ini = System.currentTimeMillis();
//		for (int x = 0; x<50; x++) {
//			ab.add(new Eleitor(UF.MA, x, 0, cand1, cand2, cand3));
//		}
//		long fim = System.currentTimeMillis();
//		System.out.println("Inserção Recursiva: "+ (((double) fim-ini)/1000) + " Segundos");
//		ini = System.currentTimeMillis();
//		for (Integer x : list) {
//			ab2.add(new Eleitor(UF.MA, x, 0, cand1, cand2, cand3),true,true);
//		}
//		fim = System.currentTimeMillis();
//		
//		System.out.println("Inserção Iterativa: "+ (((double) fim-ini)/1000) + " Segundos");
//		System.out.println("Quantidade inserida: "+qtd);
//		
//		
		
//		Eleitor busca = ab.buscaProfundidade(-1);

//		System.out.println(busca == null);

//		System.out.println(ab.raiz.getElemento());

	}
}
