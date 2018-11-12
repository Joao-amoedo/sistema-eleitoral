package pi.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.Random;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreAVL;
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

		ArvoreBinaria<Eleitor> ab1 = new ArvoreBinaria<Eleitor>();
		ArvoreAVL<Eleitor> ab2 = new ArvoreAVL<Eleitor>();

		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);

		ArvoreBinaria<Candidato> abCand = new ArvoreBinaria<Candidato>();
		abCand.add(cand1);
		abCand.add(cand2);
		abCand.add(cand3);
		
		
		
		Random rand = new Random();

		long qtd = 100000;
		for (long x = 0; x <= qtd; x++) {
			ab2.add(new Eleitor(UF.MA, x, 0, cand1, cand2, cand3));
		}
		
		long qtdBuscas = 10000;
		long inicioBusca = System.currentTimeMillis();
		for (int x = 0; x < qtdBuscas; x++) {
			ab2.buscaLargura(x);
		}
		long fimBusca = System.currentTimeMillis();
		System.out.println("Busca por largura: " + ((double)(fimBusca - inicioBusca) / 1000) + " Segundos");

		inicioBusca = System.currentTimeMillis();
		for (int x = 0; x < qtdBuscas; x++) {
			ab2.buscaBinaria(x);
		}
		fimBusca = System.currentTimeMillis();

		System.out.println("Busca Binaria: " + ((double)(fimBusca - inicioBusca) / 1000) + " Segundos");
		inicioBusca = System.currentTimeMillis();
		for (int x = 0; x < qtdBuscas; x++) {
			ab2.buscaProfundidade(x);
		}
		fimBusca = System.currentTimeMillis();
		
//		System.out.println(String.format("Busca Binaria: %.10f", ((double)(fimBusca - inicioBusca) / 1000)));
		System.out.println("Busca Profundidade: " + ((double)(fimBusca - inicioBusca) / 1000) + " Segundos");
		System.out.println("Quantidade de buscas realizadas: " + qtdBuscas);
		System.out.println("Quantidade de elementos na árvore: " + qtd);
	}
}
