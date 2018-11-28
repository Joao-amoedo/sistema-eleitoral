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
		ArvoreBinaria<Eleitor> abEl = new ArvoreBinaria<>();
		DAOEleitor.geraEleitor(abEl, abCand, 1000000);
		
	}
}
