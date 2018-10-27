package pi.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.cpf.CPF;
import pi.node.Node;

public class Teste {
	public static void main(String[] args) {
		
		

		Candidato ca1 = new Candidato("Carlitos", Partido.NOVO , TipoCandidato.FEDERAL);
		Candidato ca2 = new Candidato("Adamastor pequeno", Partido.PMDB, TipoCandidato.REGIONAL);
		Candidato ca3 = new Candidato("Lula", Partido.PT , TipoCandidato.FEDERAL);
		Candidato ca4 = new Candidato("Candidato Aleatorio", Partido.PMDB, TipoCandidato.REGIONAL);
		
		
		
		ArvoreBinariaDeBusca<Eleitor> abEleitor = new ArvoreBinariaDeBusca<Eleitor>();
		
		
		ArvoreBinaria<Candidato> abCandidato = new ArvoreBinaria<Candidato>(ca1);
		
		abCandidato.add(ca2);
		abCandidato.add(ca3);
		abCandidato.add(ca4);
		
		List<Candidato> list = abCandidato.toList();
	
		
		
		 long inicio = System.currentTimeMillis();
		DAOEleitor.geraEleitor(abEleitor, abCandidato.toList(), 100000);
		long fim = System.currentTimeMillis();
		long tempoTotal = fim - inicio;
		System.out.println(String.format("Tempo que demorou: %d",tempoTotal));
		
		//abEleitor.emOrdem();
		
//		
//		
//		List<Integer> listaString = new ArrayList<>();
//		
		
		
		

//		for (Candidato candidato : array) {
//			System.out.println(candidato.getNome());
//		}
		
	}
	
	
}

