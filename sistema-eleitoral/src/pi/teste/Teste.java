package pi.teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;

public class Teste {
	public static void main(String[] args) {
		
		ArvoreBinaria<Candidato> ab = new ArvoreBinaria<Candidato>();
		DAOCandidato.geraCandidatos(ab, 100);
		ab.emOrdem();
			
		
	}	
}

