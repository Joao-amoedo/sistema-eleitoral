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
		Eleitor el1 = new Eleitor( UF.PA, 704851661, 66, caFed, caReg);
		
		ArvoreBinaria<Eleitor> ab = new ArvoreBinaria<Eleitor>();
		ArvoreBinaria<Candidato> ab2 = new ArvoreBinaria<Candidato>();

		ArvoreBinariaDeBusca<Candidato> lerArquivo = DAOCandidato.lerArquivo();
		
		DAOEleitor.lerArquivo(lerArquivo);
		
		
		
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
