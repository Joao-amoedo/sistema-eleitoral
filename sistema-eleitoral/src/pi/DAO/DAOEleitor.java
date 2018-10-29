package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import pi.arvore.Arvore;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.UF;
import pi.model.cpf.CPF;

public class DAOEleitor {

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

	public static void geraEleitor(Arvore<Eleitor> abEleitor, List<Candidato> listCand, long qtdEleitor) {

		int contador = 0;

		Random rand = new Random();

		List<Candidato> listCandReg = new ArrayList<Candidato>();
		List<Candidato> listCandFed = new ArrayList<Candidato>();
//		List<Eleitor> listEleitor = new ArrayList<Eleitor>();

		Object[] candReg = listCand.stream().filter(a -> a.getElemento() > 1000).toArray();

		Object[] candFed = listCand.stream().filter(a -> a.getElemento() < 1000).toArray();

		for (Object cand : candReg) {
			listCandReg.add((Candidato) cand);
		}
		for (Object cand : candFed) {
			listCandFed.add((Candidato) cand);
		}

		try (FileWriter fw = new FileWriter("eleitor.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			while (contador < qtdEleitor) {

				long cpf = Long.parseLong(CPF.geraCPF());

				UF uf = UF.getUF((short) rand.nextInt(UF.values().length));
				int codigoMunicipio = rand.nextInt(1000);

				Candidato candidatoFederal = listCandFed.get(rand.nextInt(listCandFed.size()));
				Candidato candidatoRegional = listCandReg.get(rand.nextInt(listCandReg.size()));

				Eleitor eleitor = new Eleitor(uf, cpf, codigoMunicipio, candidatoFederal, candidatoRegional);
				if (abEleitor.add(eleitor)) {
					out.println(eleitor);
//				listEleitor.add(eleitor);
					contador++;
				}

			}

		} catch (IOException e) {
			// exceção
		}

//		try {
//			gravarArquivoMassa(listEleitor);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}

	}

	public static void geraEleitor(Arvore<Eleitor> abEleitor, List<Candidato> listCand, long qtdEleitor,
			boolean grava) {

		int contador = 0;

		Random rand = new Random();

		List<Candidato> listCandReg = new ArrayList<Candidato>();
		List<Candidato> listCandFed = new ArrayList<Candidato>();
		List<Eleitor> listEleitor = new ArrayList<Eleitor>();

		Object[] candReg = listCand.stream().filter(a -> a.getElemento() > 1000).toArray();

		Object[] candFed = listCand.stream().filter(a -> a.getElemento() < 1000).toArray();

		for (Object cand : candReg) {
			listCandReg.add((Candidato) cand);
		}
		for (Object cand : candFed) {
			listCandFed.add((Candidato) cand);
		}

		while (contador < qtdEleitor) {

			long cpf = Long.parseLong(CPF.geraCPF());

			UF uf = UF.getUF((short) rand.nextInt(UF.values().length));
			int codigoMunicipio = rand.nextInt(1000);

			Candidato candidatoFederal = listCandFed.get(rand.nextInt(listCandFed.size()));
			Candidato candidatoRegional = listCandReg.get(rand.nextInt(listCandReg.size()));

			Eleitor eleitor = new Eleitor(uf, cpf, codigoMunicipio, candidatoFederal, candidatoRegional);
			if (abEleitor.add(eleitor)) {
//			out.println(eleitor);
				listEleitor.add(eleitor);
				contador++;
			}

		}

		try {
			gravarArquivoMassa(listEleitor);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
