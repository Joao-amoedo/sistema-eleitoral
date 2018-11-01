package pi.teste;

import java.util.ArrayList;
import java.util.List;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.DAO.LerDiretorio;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;
import pi.node.Node;

public class TesteModel {

	public static void main(String[] args) throws Exception {

	/*	Candidato caFed = new Candidato("Adamastor Pequeno", Partido.NOVO, TipoCandidato.FEDERAL);
		Candidato caReg = new Candidato("Adamastor Pequeno", Partido.PSOL, TipoCandidato.REGIONAL);
		Eleitor el1 = new Eleitor(UF.PA, 051, 66, caFed, caReg);

		ArvoreBinaria<Eleitor> ab2 = new ArvoreBinaria<Eleitor>(el1, false);

//		System.out.println(el1);
//		
		long inicioLeitura = System.currentTimeMillis();
		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivo();
//		ArvoreBinaria<Eleitor> ab2 = DAOEleitor.lerArquivo(abCand);
		long fimLeitura = System.currentTimeMillis();
		long tempoTotalLeitura = fimLeitura - inicioLeitura;

		long inicioGeracao = System.currentTimeMillis();
		DAOEleitor.geraEleitor(ab2, abCand.toList(), 5000);
		long fimGeracao = System.currentTimeMillis();
		long tempoTotalGeracao = fimGeracao - inicioGeracao;

		System.out.println(String.format("Tempo que demorou para gerar: %d/ms", tempoTotalGeracao));
		System.out.println(String.format("Tempo que demorou para ler: %d/ms", tempoTotalLeitura));
		System.out.println(ab2.getRaiz().getConteudo().getCandidatoRegional().getQtdVotos());
//		ab2.emOrdem();
		System.out.println(ab2.toList().size());
//		ab2.emOrdem();
*/
		
		
//		ab2.emOrdem();
//
//		ArvoreBinariaDeBusca<Candidato> lerArquivo = DAOCandidato.lerArquivo();
//		
//		DAOEleitor.lerArquivo(lerArquivo);
//		
//
//		ArvoreBinariaDeBusca<Candidato> abbCand = DAOCandidato.lerArquivo();
//
//		DAOEleitor.lerArquivo(abbCand);
//		System.out.println(abbCand);

//		lista.add(caFed);
//		lista.add(caReg);
//		
//		DAOEleitor daoEleitor = new DAOEleitor();
//		DAOCandidato daoCandidato = new DAOCandidato();
//		
//		
//		
//		String toStringEleitor = el1.toString();
//		String toStringCandidato = caFed.toString();
		// daoCandidato.gravarArquivo(toStringCandidato);
		
		
		
//		ArvoreBinariaDeBusca<Candidato> elemento = new ArvoreBinariaDeBusca<Candidato>();
//		System.out.println(elemento.toString());

	}
}
