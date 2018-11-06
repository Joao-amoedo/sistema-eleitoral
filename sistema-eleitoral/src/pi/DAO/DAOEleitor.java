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
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.TipoCandidato;
import pi.model.UF;
import pi.model.cpf.CPF;

public class DAOEleitor {

	private static List<Candidato> regional = new ArrayList<Candidato>();
	private static List<Candidato> federal = new ArrayList<Candidato>();
	private static List<Candidato> presidenciavel = new ArrayList<Candidato>();

	public static void gravarArquivoUnico(String texto) throws Exception {
		try (FileWriter fw = new FileWriter("eleitor.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}

	public static void gravarArquivoMassa(List<Eleitor> list) throws Exception {
		try (FileWriter fw = new FileWriter("eleitor.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			list.forEach(c -> out.println(c));

		} catch (IOException e) {
			// exceção
		}
	}

	public static ArvoreBinariaDeBusca<Eleitor> lerArquivo(ArvoreBinariaDeBusca<Candidato> ab) {

		ArvoreBinariaDeBusca<Eleitor> abEleitor = new ArvoreBinariaDeBusca<Eleitor>();

		try (Scanner sc = new Scanner(new File("eleitor.txt"))) {
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
				int codCandidatoPresidenciavel = Integer.parseInt(split[9]);

				Candidato candidatoFederal = ab.buscaBinaria(codCandidatoFederal);
				Candidato candidatoRegional = ab.buscaBinaria(codCandidatoRegional);
				Candidato candidatoPresidenciavel = ab.buscaBinaria(codCandidatoPresidenciavel);

				Eleitor eleitor = new Eleitor(uf, cpf, sequencial, codigoDoMunicipio, candidatoFederal,
						candidatoRegional, candidatoPresidenciavel);

				abEleitor.add(eleitor);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return abEleitor;

	}

	public static void geraEleitor(Arvore<Eleitor> abEleitor, ArvoreBinaria<Candidato> ab, long qtdEleitor) {

//		try (FileWriter fw = new FileWriter("eleitor.txt", true);
//				BufferedWriter bw = new BufferedWriter(fw);
//				PrintWriter out = new PrintWriter(bw)) {

			int contador = 0;

			Random rand = new Random();
			
			populaListas(ab);

			while (contador < qtdEleitor) {

				long cpf = Long.parseLong(CPF.geraCPF());
				UF uf = UF.getUF((short) rand.nextInt(UF.values().length));
				int codigoMunicipio = rand.nextInt(1000);
				Candidato candidatoFederal = federal.get(rand.nextInt(federal.size()));
				Candidato candidatoRegional = regional.get(rand.nextInt(regional.size()));
				Candidato candidatoPres = presidenciavel.get(rand.nextInt(presidenciavel.size()));
				Eleitor eleitor = new Eleitor(uf, cpf, codigoMunicipio, candidatoFederal, candidatoRegional,
						candidatoPres);

				if (abEleitor.add(eleitor)) {
//					out.println(eleitor.toString());
					contador++;
				}

			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private static void populaListas(ArvoreBinaria<Candidato> ab) {
		Arrays.asList(ab.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.FEDERAL)
				.forEach(c -> federal.add((Candidato) c));

		Arrays.asList(ab.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.PRESIDENCIAVEL)
				.forEach(c -> presidenciavel.add((Candidato) c));

		Arrays.asList(ab.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.REGIONAL)
				.forEach(c -> regional.add((Candidato) c));
	}
}