package pi.teste;

import java.util.ArrayList;
import java.util.List;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
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

		Candidato caFed = new Candidato("Adamastor Pequeno",
				Partido.NOVO, TipoCandidato.FEDERAL);
		Candidato caReg = new Candidato("Adamastor Pequeno",
				Partido.PSOL, TipoCandidato.REGIONAL);
		Eleitor el1 = new Eleitor( UF.PA, 051, 66, caFed, caReg);
		
//		System.out.println(el1);
//		
		long inicio = System.currentTimeMillis();

		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivo();
		
		ArvoreBinaria<Eleitor> ab2 = DAOEleitor.lerArquivo(abCand);
		DAOEleitor.geraEleitor(ab2, abCand.toList(), 1);
		
		long fim = System.currentTimeMillis();
		long tempoTotal = fim - inicio;
		System.out.println(String.format("Tempo que demorou: %d",tempoTotal));
		System.out.println(ab2.getRaiz().getConteudo().getCandidatoRegional().getQtdVotos());
		
//		ab2.emOrdem();
		
//		ab2.emOrdem();
//
//		ArvoreBinariaDeBusca<Candidato> lerArquivo = DAOCandidato.lerArquivo();
//		
//		DAOEleitor.lerArquivo(lerArquivo);
//		
		
		
//		ArvoreBinariaDeBusca<Candidato> abbCand = DAOCandidato.lerArquivo();

		

//		DAOEleitor.lerArquivo(abbCand);

		
		
		
//		List<Candidato> lista = new ArrayList<Candidato>();
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
//		
//
//		
//		daoEleitor.lerArquivo(lista);
//		
		//daoCandidato.gravarArquivo(toStringCandidato);

		

	}
}
