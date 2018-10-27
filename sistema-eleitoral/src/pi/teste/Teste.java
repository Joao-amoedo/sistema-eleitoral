package pi.teste;

import java.util.Random;

import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.node.Node;

public class Teste {
	public static void main(String[] args) {
		
		

		Candidato ca1 = new Candidato("Carlitos", Partido.NOVO , TipoCandidato.FEDERAL);
		Candidato ca2 = new Candidato("Adamastor pequeno", Partido.PMDB, TipoCandidato.REGIONAL);
		Candidato ca3 = new Candidato("Lula", Partido.PT , TipoCandidato.FEDERAL);
		
		
				
				
		ArvoreBinariaDeBusca<Candidato> ab = new ArvoreBinariaDeBusca<Candidato>(ca1);
		
		ab.add(ca2);
		ab.add(ca3);
		
		Node<Candidato> node = ab.buscaBinaria(2);
		System.out.println(node.getConteudo());
	}
	
	
}

