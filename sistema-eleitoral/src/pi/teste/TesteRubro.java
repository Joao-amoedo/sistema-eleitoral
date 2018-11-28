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

		ArvoreRubroNegra<Eleitor> abRubro = new ArvoreRubroNegra<Eleitor>();
		ArvoreAVL<Eleitor> abAVL = new ArvoreAVL<Eleitor>();

		Random rand = new Random();
		List<Eleitor> list = new ArrayList<Eleitor>();
		Candidato cand1 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand2 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);

<<<<<<< HEAD
		int qtdBusca = 100;
		int qtdAdd = 1000000;
=======

		int qtdBusca = 50;
		int qtdAdd = 1000;

>>>>>>> Urna pronta.. pelo menos para apresentar
		for (int x = 0; x < qtdAdd; x++) {
			list.add(new Eleitor(UF.MA, rand.nextLong(), 0, cand1, cand2));
		}

		long iniRubro = System.currentTimeMillis();
		for (Eleitor eleitor : list) {
			abRubro.add(eleitor);
		}
		long fimRubro = System.currentTimeMillis();
		long iniAVL = System.currentTimeMillis();

		for (Eleitor eleitor : list) {
			abAVL.add(eleitor);
		}
		long fimAVL = System.currentTimeMillis();
		System.out.println("\tArvore Rubro Negra");
		System.out.println("Tempo que demorou para inserir na Rubro: " + (((double) fimRubro - iniRubro) / 1000) + " Segundos");
		System.out.println("RAIZ: " + abRubro.raiz.getElemento());
		System.out.println("Tamanho da arvoreRubro: " + abRubro.toList().size());
		System.out.println("-------");
		System.out.println("\tArvore AVL");
		System.out.println("Tempo que demorou para inserir na AVL: " + (((double) fimAVL - iniAVL) / 1000) + " Segundos");
		System.out.println("RAIZ: " + abAVL.raiz.getElemento());
		System.out.println("Tamanho da arvoreAVL: " + abAVL.toList().size());
		System.out.println("----------");
		System.out.println("\tBusca na Arvore Rubro Negra");
		List<Integer> listInt = new ArrayList<Integer>();
		for(int x = 0; x < qtdBusca; x++) {
			listInt.add(rand.nextInt());
		}

		long ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abRubro.buscaBinaria(x);
		}
		long fim = System.currentTimeMillis();

		System.out.println("Busca Binaria na Rubro Negra: " + (((double) fim- ini) / 1000) + " Segundos");

		ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abRubro.buscaProfundidade(x);
		}
		fim = System.currentTimeMillis();
		System.out.println("Busca Profundidade na Rubro Negra: " + (((double) fim- ini) / 1000) + " Segundos");

		ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abRubro.buscaLargura(x);
		}
		fim = System.currentTimeMillis();
		System.out.println("Busca Largura na Rubro Negra: " + (((double) fim- ini) / 1000) + " Segundos");
		System.out.println("Quantidade de elementos pesquisados: " + qtdBusca);

		System.out.println("---------");
		System.out.println("\tBusca na �rvore AVl");
		ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abAVL.buscaBinaria(x);
		}
		fim = System.currentTimeMillis();

		System.out.println("Busca Binaria na AVL: " + (((double) fim- ini) / 1000) + " Segundos");

		ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abAVL.buscaProfundidade(x);
		}
		fim = System.currentTimeMillis();
		System.out.println("Busca Profundidade na AVL " + (((double) fim- ini) / 1000) + " Segundos");

		ini = System.currentTimeMillis();
		for (Integer x : listInt) {
			abAVL.buscaLargura(x);
		}
		fim = System.currentTimeMillis();
		System.out.println("Busca Largura na AVL: " + (((double) fim- ini) / 1000) + " Segundos");
		System.out.println("Quantidade de elementos pesquisados: " + qtdBusca);
	}
}
