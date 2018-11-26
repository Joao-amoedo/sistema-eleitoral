package pi.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

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
		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivos();
		long ini = System.currentTimeMillis();//
		ArvoreBinariaDeBusca<Eleitor> abEleitor = DAOEleitor.lerArquivo(abCand, 1);
		long fim = System.currentTimeMillis();
//		System.out.println("Tempo para ler: " + ((double) fim - ini) / 1000 + " Segundos");
		DAOEleitor.geraEleitor(abEleitor, abCand, 30000000);
		Candidato CandRegionalVencedor = abCand
				.toList()
				.stream()
				.filter(c -> c.getTipoCandidato() == TipoCandidato.REGIONAL)
				.max((c1, c2) -> Long.compare(c1.getQtdVotos(), c2.getQtdVotos()))
				.get();

		Candidato CandFederalVencedor = abCand
				.toList()
				.stream()
				.filter(c -> c.getTipoCandidato() == TipoCandidato.FEDERAL)
				.max((c1, c2) -> Long.compare(c1.getQtdVotos(), c2.getQtdVotos()))
				.get();
		
		Candidato CandPresidenciavelVencedor = abCand
				.toList()
				.stream()
				.filter(c -> c.getTipoCandidato() == TipoCandidato.PRESIDENCIAVEL)
				.max((c1, c2) -> Long.compare(c1.getQtdVotos(), c2.getQtdVotos()))
				.get();

		System.out.println(CandRegionalVencedor);
		System.out.println("Quantidade de votos: "+ CandRegionalVencedor.getQtdVotos());
		System.out.println(CandFederalVencedor);
		System.out.println("Quantidade de votos: "+ CandFederalVencedor.getQtdVotos());
		System.out.println(CandPresidenciavelVencedor);
		System.out.println("Quantidade de votos: "+ CandPresidenciavelVencedor.getQtdVotos());
		
	}
}
