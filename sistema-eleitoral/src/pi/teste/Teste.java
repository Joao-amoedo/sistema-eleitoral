package pi.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pi.DAO.DAOCandidato;
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
		
		
//
//		Candidato ca1 = new Candidato("Carlitos", Partido.NOVO , TipoCandidato.FEDERAL);
//		Candidato ca2 = new Candidato("Adamastor pequeno", Partido.PMDB, TipoCandidato.REGIONAL);
//		Candidato ca3 = new Candidato("Lula Livre", Partido.PT , TipoCandidato.FEDERAL);
//		Candidato ca4 = new Candidato("Candidato Aleatorio", Partido.PMDB, TipoCandidato.REGIONAL);
		
		
		
		
//		ArvoreBinaria<Eleitor> abEleitor = new ArvoreBinaria<Eleitor>();
		long inicio = System.currentTimeMillis();
		
		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivo();
//		ArvoreBinaria<Eleitor> abEleitor = DAOEleitor.lerArquivo(abCand);
		
//		abEleitor.emOrdem();
//		abEleitor.emOrdem();
		
//		
//		abCandidato.add(ca2,true);
//		abCandidato.add(ca3,true);
//		abCandidato.add(ca4,true);
//		
//		List<Candidato> list = abCandidato.toList();
//	
//		
//		
//		DAOEleitor.geraEleitor(abEleitor, abCand.toList(), 100);
		long fim = System.currentTimeMillis();
		long tempoTotal = fim - inicio;
		System.out.println(String.format("Tempo que demorou: %d",tempoTotal));
//		
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

