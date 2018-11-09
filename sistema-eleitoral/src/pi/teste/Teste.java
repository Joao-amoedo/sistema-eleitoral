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
		
		long inicioBinaria = System.currentTimeMillis();
		long qtd = 10000000;
		for(long x = 0; x <= qtd; x++) {
			ab1.add(new Eleitor(UF.MA,rand.nextLong(),0,cand1,cand2,cand3));
		}
		long fimBinaria = System.currentTimeMillis();
		ab1 = null;
		
		long inicioAVL= System.currentTimeMillis();
		for(long x = 0; x <= qtd ; x++) {
			ab2.add(new Eleitor(UF.MA,rand.nextLong(),0,cand1,cand2,cand3));
		}
		long fimAVL = System.currentTimeMillis();
		System.out.println(String.format("Tempo da binaria: %d", fimBinaria-inicioBinaria ));;
		System.out.println(String.format("Tempo da AVL: %d", fimAVL-inicioAVL));

		
	}

}
