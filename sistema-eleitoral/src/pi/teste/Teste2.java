package pi.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;
import sun.security.x509.URIName;
import pi.main.UrnaEletronica;
public class Teste2 {

	public static void main(String[] args) {
<<<<<<< HEAD
		UrnaEletronica.menu();
=======

		List<Eleitor> list = new ArrayList<Eleitor>();
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);


		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();
		for(int x = 1; x <= 6; x++)
			ab.add(new Eleitor(UF.MA, x, 0, cand1, cand2, cand3));
		ab.remove(4);
		ab.emOrdem();
		
>>>>>>> branch 'arvore_rubro_nebra' of https://github.com/joao-amoedo/sistema-eleitoral
		
	}
}
