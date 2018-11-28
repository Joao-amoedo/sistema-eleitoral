package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import pi.arvore.Arvore;
import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreBalanceada;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.arvore.ArvoreRubroNegra;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.TipoCandidato;
import pi.model.UF;
import pi.model.cpf.CPF;

public class DAOEleitor {

	private static List<Candidato> regional = new ArrayList<Candidato>();
	private static List<Candidato> federal = new ArrayList<Candidato>();
	private static List<Integer> codigoMunicipioTO = new ArrayList<Integer>();
	private static List<Integer> codigoMunicipioPA = new ArrayList<Integer>();
	private static List<Integer> codigoMunicipioMA = new ArrayList<Integer>();
	private static Random rand = new Random();
	private static boolean arquivosLidos = false;

	public static void lerCodigo() {
		lerArquivos("eleitores//codigoMunicipioTO", codigoMunicipioTO);
		lerArquivos("eleitores//codigoMunicipioPA", codigoMunicipioPA);
		lerArquivos("eleitores//codigoMunicipioMA", codigoMunicipioMA);
		arquivosLidos = true;
	}

	private static int getCodigoAleatorio(UF uf) {
		if (uf == UF.TO) {
			return codigoMunicipioTO.get(rand.nextInt(codigoMunicipioTO.size()));
		} else if (uf == UF.MA) {
			return codigoMunicipioMA.get(rand.nextInt(codigoMunicipioMA.size()));
		} else {
			return codigoMunicipioPA.get(rand.nextInt(codigoMunicipioPA.size()));
		}
	}

	public static void lerArquivos(String nomeArquivo, List<Integer> lista) {
		try (Scanner sc = new Scanner(new File(nomeArquivo))) {
			while (sc.hasNextLine()) {
				lista.add(Integer.parseInt(sc.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void gravarArquivoUnico(String texto) throws Exception {
		try (FileWriter fw = new FileWriter("eleitores\\votos.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}


	/**
	 * Lê o arquivo de Eleitor e retorna uma árvore binária populada. O parâmetro
	 * tipoArvore define o tipo da árvore que será criada 1 - ArvoreRubroNegra; 2 -
	 * ArvoreAVL; Outro - ArvoreBinariaDeBusca
	 * 
	 * @param ab
	 * @param tipoArvore
	 * @return arvore
	 */
	
	public static ArvoreBinariaDeBusca<Eleitor> lerArquivo(ArvoreBinariaDeBusca<Candidato> ab, int tipoArvore) {

		ArvoreBinariaDeBusca<Eleitor> abEleitor = null;
		if (tipoArvore == 1)
			abEleitor = new ArvoreRubroNegra<Eleitor>();
		else if (tipoArvore == 2)
			abEleitor = new ArvoreAVL<Eleitor>();
		else
			abEleitor = new ArvoreBinariaDeBusca<Eleitor>();


		try (Scanner sc = new Scanner(new File("eleitores\\votos.txt"))) {
			sc.nextLine();
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				String[] split = linha.split(";");

				UF uf = UF.valueOf(split[1]);

				long cpf = Long.parseLong(split[2].replace(".", "").replace("-", ""));

				long sequencial = Integer.parseInt(split[3]);
				int codigoDoMunicipio = Integer.parseInt(split[4]);

				int codCandidatoFederal = Integer.parseInt(split[5]);
				int codCandidatoRegional = Integer.parseInt(split[7]);

				Candidato candidatoFederal = ab.buscaBinaria(codCandidatoFederal);
				Candidato candidatoRegional = ab.buscaBinaria(codCandidatoRegional);

				Eleitor eleitor = new Eleitor(uf, cpf, sequencial, codigoDoMunicipio, candidatoFederal,
						candidatoRegional);

				abEleitor.add(eleitor);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return abEleitor;

	}

	public static void geraEleitor(Arvore<Eleitor> abEleitor, ArvoreBinaria<Candidato> ab, long qtdEleitor) {
		if (!arquivosLidos)
			lerCodigo();
		try (FileWriter fw = new FileWriter("eleitores\\votos.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			int contador = 0;

			Random rand = new Random();

			populaListas(ab);

			while (contador < qtdEleitor) {

				long cpf = Long.parseLong(CPF.geraCPF());
				UF uf = UF.getUF((short) rand.nextInt(UF.values().length));
				int codigoMunicipio = getCodigoAleatorio(uf);
				Candidato candidatoFederal = federal.get(rand.nextInt(federal.size()));
				Candidato candidatoRegional = regional.get(rand.nextInt(regional.size()));
				Eleitor eleitor = new Eleitor(uf, cpf, codigoMunicipio, candidatoFederal, candidatoRegional);

				if (abEleitor.add(eleitor)) {
					out.println(eleitor.toString());
					contador++;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void populaListas(ArvoreBinaria<Candidato> ab) {
		Arrays.asList(ab.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.FEDERAL)
				.forEach(c -> federal.add((Candidato) c));

		Arrays.asList(ab.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.REGIONAL)
				.forEach(c -> regional.add((Candidato) c));
	}
}