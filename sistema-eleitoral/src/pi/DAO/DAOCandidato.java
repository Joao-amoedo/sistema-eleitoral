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
			// exce��o
		}
	}
	
	private static void lerArquivo(String nomeArquivo, ArvoreBinariaDeBusca<Candidato>abb) {
		try(Scanner sc = new Scanner(new File(nomeArquivo))){
			sc.nextLine();
			while(sc.hasNextLine()) {
				String linha = sc.nextLine();
				String[] split = linha.split(";");
				
				short codigoCandidato = Short.parseShort(split[1]);
				String nomeCandidato = split[2];
				Partido partido = Partido.getPartido(Integer.parseInt(split[3]));
				TipoCandidato tp = null;
				
				if(split[1].length() < 4)
					tp = TipoCandidato.FEDERAL;
				else if(split[1].length() == 4)
					tp = TipoCandidato.REGIONAL;
				else
					tp = TipoCandidato.PRESIDENCIAVEL;

				Candidato cad = new Candidato(nomeCandidato,partido,tp,codigoCandidato);
				
				abb.add(cad);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void geraCandidatos(ArvoreBinaria<Candidato> ab, int qtdCandidatos) {
		int contador = 0;
		
		while(contador<qtdCandidatos) {
			TipoCandidato tipoCandidato = TipoCandidato.getTipoCandidatoAleatorio();
			String nome = String.format("Candidato %d", Candidato.getCodigoCandidatos() + 1);
			Partido partido = Partido.getPartidoAleatorio();
			
			Candidato candidato = new Candidato(nome,partido,tipoCandidato);
			if(ab.add(candidato,true))
				contador++;
		}
	}
	
	public static ArvoreBinariaDeBusca<Candidato> lerArquivos() {
		ArvoreBinariaDeBusca<Candidato> ab = new ArvoreBinariaDeBusca<Candidato>();
		DAOCandidato.lerArquivo("candidatos//candidato.txt",ab);
//		DAOCandidato.lerArquivo("candidatos//presidente.txt",ab);
		return ab;
	}
}
