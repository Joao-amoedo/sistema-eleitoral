package pi.teste;

import java.util.ArrayList;
import java.util.List;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;

public class Teste {
	public static void main(String[] args) {
		
		

//		Candidato ca1 = new Candidato("Bolus", Partido.PT , TipoCandidato.PRECIDENTE);
//		Candidato ca2 = new Candidato("Marina", Partido.PMDB, TipoCandidato.REGIONAL);
//		Candidato ca3 = new Candidato("LULA", Partido.NOVO , TipoCandidato.FEDERAL);
//		Candidato ca4 = new Candidato("Candidato Aleatorio", Partido.PMDB, TipoCandidato.REGIONAL);
//		
//		ArvoreBinaria<Candidato> candidato = new ArvoreBinaria<Candidato>(ca1, true);
//		candidato = new ArvoreBinaria<Candidato>(ca2, true);
//		candidato = new ArvoreBinaria<Candidato>(ca3, true);
		
		
		
		
//		ArvoreBinaria<Eleitor> abEleitor = new ArvoreBinaria<Eleitor>();
//		long inicio = System.currentTimeMillis();
//		
//		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivo();
//		ArvoreBinaria<Eleitor> abEleitor = DAOEleitor.lerArquivo(abCand);
//		
//		abEleitor.emOrdem();
//		abEleitor.emOrdem();
//		
//		
//		abCand.add(ca2,true);
//		abCandidato.add(ca3,true);
//		abCandidato.add(ca4,true);
		
//		List<Candidato> list = abCandidato.toList();
//	
//		
//		
//		DAOEleitor.geraEleitor(abEleitor, abCand.toList(), 100);
//		long fim = System.currentTimeMillis();
//		long tempoTotal = fim - inicio;
//		System.out.println(String.format("Tempo que demorou: %d",tempoTotal));
//		
		//abEleitor.emOrdem();
		
//		
//		
//		List<Integer> listaString = new ArrayList<>();
//		
		
		ArvoreBinariaDeBusca<Candidato> ab = DAOCandidato.lerArquivos();
		
//		
		
		
//		abEleitor.emOrdem();
		//System.out.println(abCand.emOrdem());

		
	}	
}

