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
import pi.model.cpf.CPF;
import sun.security.x509.URIName;
import pi.main.UrnaEletronica;

public class Teste2 {

	public static void main(String[] args) {
<<<<<<< HEAD
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
=======
		
//		System.out.println(CPF.validaCPF2("70485166251"));
		UrnaEletronica.menu();
//		ArvoreBinariaDeBusca<Candidato> abCand = DAOCandidato.lerArquivos();
//		comparaBusca(1000000, abCand);
>>>>>>> Arrumando alguns retoques finais antes da apresentação
	}

	private static void comparaBusca(int quantidade, ArvoreBinariaDeBusca<Candidato> abCandidato) {
		try (FileWriter fw3 = new FileWriter("ComparacaoBinaria.txt", true);
				BufferedWriter bw3 = new BufferedWriter(fw3);
				PrintWriter outBi = new PrintWriter(bw3)) {

			try (FileWriter fw = new FileWriter("ComparacaoProfundidade.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter outProf = new PrintWriter(bw)) {

				try (FileWriter fw2 = new FileWriter("comparacaoLargura.txt", true);
						BufferedWriter bw2 = new BufferedWriter(fw2);
						PrintWriter outLarg = new PrintWriter(bw2)) {

					int contador = 10000;
					Random rand = new Random();
					List<Object> listRegional = Arrays.asList((abCandidato.toList().stream()
							.filter(c -> c.getTipoCandidato() == TipoCandidato.REGIONAL).toArray()));
					List<Object> listFederal = Arrays.asList((abCandidato.toList().stream()
							.filter(c -> c.getTipoCandidato() == TipoCandidato.FEDERAL).toArray()));
					while (contador <= quantidade) {
						ArvoreAVL<Eleitor> abRubro = new ArvoreAVL<Eleitor>();
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
						for (Eleitor eleitor : list) {
							abAVL.add(eleitor);
						}

						int numeroAleatorio = rand.nextInt();
						List<Integer> lista = new ArrayList<Integer>();

						for (int x = 0; x < 10; x++) {
							lista.add(rand.nextInt());
						}

						long iniBuscaRubro = System.currentTimeMillis();
						lista.forEach(c -> abRubro.buscaProfundidade(c));
						long fimBuscaRubro = System.currentTimeMillis();
						long iniBuscaAVL = System.currentTimeMillis();
						lista.forEach(c -> abAVL.buscaProfundidade(c));
						long fimBuscaAVL = System.currentTimeMillis();

//					for (Eleitor eleitor : list) {
//						abAVL.add(eleitor);
//					}
//					long fimAVL = System.currentTimeMillis();

						outProf.println(contador + ";" + (((double) fimBuscaRubro - iniBuscaRubro) / 1000) + ";"
								+ (((double) fimBuscaAVL - iniBuscaAVL) / 1000) + ";");

						iniBuscaRubro = System.currentTimeMillis();
						lista.forEach(c -> abRubro.buscaLargura(c));
						fimBuscaRubro = System.currentTimeMillis();
						iniBuscaAVL = System.currentTimeMillis();
						lista.forEach(c -> abAVL.buscaLargura(c));
						fimBuscaAVL = System.currentTimeMillis();

						outLarg.println(contador + ";" + (((double) fimBuscaRubro - iniBuscaRubro) / 1000) + ";"
								+ (((double) fimBuscaAVL - iniBuscaAVL) / 1000) + ";");

						iniBuscaRubro = System.currentTimeMillis();
						lista.forEach(c -> abRubro.buscaBinaria(c));
						fimBuscaRubro = System.currentTimeMillis();
						iniBuscaAVL = System.currentTimeMillis();
						lista.forEach(c -> abAVL.buscaBinaria(c));
						fimBuscaAVL = System.currentTimeMillis();

						outBi.println(contador + ";" + (((double) fimBuscaRubro - iniBuscaRubro) / 1000) + ";"
								+ (((double) fimBuscaAVL - iniBuscaAVL) / 1000) + ";");
						contador += 10000;

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
>>>>>>> Mudanças sutis
	}

//	private static void comparaBuscaArvores(ArvoreBinaria<Candidato> ab, int quantidade) {
//
//		try (FileWriter fw2 = new FileWriter("ComparaBusca.txt", true);
//				BufferedWriter bw2 = new BufferedWriter(fw2);
//				PrintWriter outAVL = new PrintWriter(bw2)) {
//			int contador = 10000;
//			Random rand = new Random();
//			List<Object> listRegional = Arrays.asList((abCandidato.toList().stream()
//					.filter(c -> c.getTipoCandidato() == TipoCandidato.REGIONAL).toArray()));
//			List<Object> listFederal = Arrays.asList((abCandidato.toList().stream()
//					.filter(c -> c.getTipoCandidato() == TipoCandidato.FEDERAL).toArray()));
//			while (contador <= quantidade) {
//				ArvoreRubroNegra<Eleitor> abRubro = new ArvoreRubroNegra<Eleitor>();
//				ArvoreAVL<Eleitor> abAVL = new ArvoreAVL<Eleitor>();
//				List<Eleitor> list = new ArrayList<Eleitor>();
//				for (int x = 0; x < contador; x++) {
//					Candidato candRegional = ((Candidato) listRegional.get(rand.nextInt(listRegional.size())));
//					Candidato candFederal = ((Candidato) listFederal.get(rand.nextInt(listFederal.size())));
//					list.add(new Eleitor(UF.MA, rand.nextLong(), 0, candFederal, candRegional));
//				}
//				for (Eleitor eleitor : list) {
//					abRubro.add(eleitor);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
}
