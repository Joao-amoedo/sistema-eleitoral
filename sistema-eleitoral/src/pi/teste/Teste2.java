package pi.teste;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
import sun.security.x509.URIName;
import pi.main.UrnaEletronica;

public class Teste2 {

	public static void main(String[] args) {
<<<<<<< HEAD

		List<Eleitor> list = new ArrayList<Eleitor>();
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);


		ArvoreRubroNegra<Eleitor> ab = new ArvoreRubroNegra<Eleitor>();
		for(int x = 1; x <= 6; x++)
			ab.add(new Eleitor(UF.MA, x, 0, cand1, cand2, cand3));
		ab.remove(4);
		ab.emOrdem();


=======

<<<<<<< HEAD
		UrnaEletronica.menu();
		
		
		
>>>>>>> Urna pronta.. pelo menos para apresentar
=======
//		UrnaEletronica.menu();
		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivos();
		testaMuita(1000000, abCand);
	}

	private static void testaMuita(int quantidade, ArvoreBinariaDeBusca<Candidato> abCandidato) {
		try (FileWriter fw = new FileWriter("tempoRubro.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outRubro = new PrintWriter(bw)) {

			try (FileWriter fw2 = new FileWriter("tempoAVL.txt", true);
					BufferedWriter bw2 = new BufferedWriter(fw2);
					PrintWriter outAVL = new PrintWriter(bw2)) {
				int contador = 10000;
				Random rand = new Random();
				List<Object> listRegional = Arrays.asList((abCandidato.toList().stream()
						.filter(c -> c.getTipoCandidato() == TipoCandidato.REGIONAL).toArray()));
				List<Object> listFederal = Arrays.asList((abCandidato.toList().stream()
						.filter(c -> c.getTipoCandidato() == TipoCandidato.FEDERAL).toArray()));
				while (contador <= quantidade) {
					ArvoreRubroNegra<Eleitor> abRubro = new ArvoreRubroNegra<Eleitor>();
					ArvoreAVL<Eleitor> abAVL = new ArvoreAVL<Eleitor>();
					List<Eleitor> list = new ArrayList<Eleitor>();
					for (int x = 0; x < contador; x++) {
						Candidato candRegional = ((Candidato) listRegional.get(rand.nextInt(listRegional.size())));
						Candidato candFederal = ((Candidato) listFederal.get(rand.nextInt(listFederal.size())));
						list.add(new Eleitor(UF.MA, rand.nextLong(), 0, candFederal, candRegional));
					}
					for (Eleitor eleitor : list) {
						abRubro.add(eleitor);
					}
					int numeroAleatorio = rand.nextInt();
					long iniBinaria = System.currentTimeMillis();
					abRubro.buscaBinaria(numeroAleatorio);
					long fimBinaria = System.currentTimeMillis();
					long iniProfundidade = System.currentTimeMillis();
					abRubro.buscaProfundidade(numeroAleatorio);
					long fimProfundidade= System.currentTimeMillis();
					long iniLargura= System.currentTimeMillis();
					abRubro.buscaLargura(numeroAleatorio);
					long fimLargura = System.currentTimeMillis();
//					long iniAVL = System.currentTimeMillis();

//					for (Eleitor eleitor : list) {
//						abAVL.add(eleitor);
//					}
//					long fimAVL = System.currentTimeMillis();

					outRubro.println(contador +";" + (((double) fimBinaria - iniBinaria) / 1000) + ";" + (((double) fimProfundidade- iniProfundidade) / 1000) + ";" +  (((double) fimLargura- iniLargura) / 1000));
					contador += 10000;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
>>>>>>> Mudanças sutis
	}
}
