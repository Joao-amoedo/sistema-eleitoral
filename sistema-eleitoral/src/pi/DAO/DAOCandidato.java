package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;

public class DAOCandidato {
	
	public static void gravarArquivo(String texto) throws Exception {

		try (FileWriter fw = new FileWriter("candidato.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}
	
	public static ArvoreBinariaDeBusca<Candidato> lerArquivo() throws FileNotFoundException {
		ArvoreBinariaDeBusca<Candidato> abb = new ArvoreBinariaDeBusca<Candidato>();
		try(Scanner sc = new Scanner(new File("candidato.txt"))){
			sc.nextLine();
			while(sc.hasNextLine()) {
				String linha = sc.nextLine();
				String[] split = linha.split(";");
				
				short codigoCandidato = Short.parseShort(split[1]);
				String nomeCandidato = split[2];
				Partido partido = Partido.getPartido(Integer.parseInt(split[3]));
				TipoCandidato tp = null;
				
				if(split[1].length() <= 3)
					tp = TipoCandidato.FEDERAL;
				else
					tp = TipoCandidato.REGIONAL;

				Candidato cad = new Candidato(nomeCandidato,partido,tp,codigoCandidato);
				abb.add(cad);
				
			}
		}
		return abb;
	}
}
