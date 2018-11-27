package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Partido;
import pi.model.TipoCandidato;

public class DAOCandidato {

	public static void gravarArquivo(String texto) throws Exception {

		try (FileWriter fw = new FileWriter("candidatos//candidato.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {

		}
	}
	/**
	 * Lê linha a linha do arquivo, separa cada pedaço pelo ';', cria um novo candidato e o poe na árvore
	 * @param nomeArquivo
	 * @param ab
	 */

	private static void lerArquivo(String nomeArquivo, ArvoreBinariaDeBusca<Candidato> ab) {
		try (Scanner sc = new Scanner(new File(nomeArquivo))) {
			sc.nextLine();
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				String[] split = linha.split(";");

				short codigoCandidato = Short.parseShort(split[1].replace(" ", ""));
				String nomeCandidato = split[2];
				Partido partido = Partido.getPartido(Integer.parseInt(split[3].replace(" ", "")));
				TipoCandidato tp = null;

				if (split[1].length() < 5)
					tp = TipoCandidato.FEDERAL;
				else if (split[1].length() >= 5)
					tp = TipoCandidato.REGIONAL;

				Candidato cad = new Candidato(nomeCandidato, partido, tp, codigoCandidato);

				ab.add(cad);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void geraCandidatos(ArvoreBinaria<Candidato> ab, int qtdCandidatos) {
		int contador = 0;
		try (FileWriter fw = new FileWriter("candidatos\\candidato.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			while (contador < qtdCandidatos) {
				TipoCandidato tipoCandidato = TipoCandidato.REGIONAL;
				String nome = String.format("Candidato %d", Candidato.getCodigoCandidatos() + 1);
				Partido partido = Partido.getPartidoAleatorio();

				Candidato candidato = new Candidato(nome, partido, tipoCandidato);
				if (ab.add(candidato, true))
					contador++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArvoreBinariaDeBusca<Candidato> lerArquivos() {
		ArvoreBinariaDeBusca<Candidato> ab = new ArvoreBinariaDeBusca<Candidato>();
		DAOCandidato.lerArquivo("candidatos//candidato.txt", ab);
		DAOCandidato.lerArquivo("candidatos//presidente.txt", ab);
		return ab;
	}
}
