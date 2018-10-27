package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import pi.arvore.Arvore;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.UF;
import pi.node.Node;

public class DAOEleitor {

	public static void gravarArquivo(String texto) throws Exception {
		try (FileWriter fw = new FileWriter("eleitor.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}
	
	public static ArvoreBinariaDeBusca<Eleitor> lerArquivo(ArvoreBinariaDeBusca<Candidato> ab) throws FileNotFoundException {
		ArvoreBinariaDeBusca<Eleitor> abb = new ArvoreBinariaDeBusca<Eleitor>();
		try(Scanner sc = new Scanner(new File("eleitor.txt"))){
			sc.nextLine();
			while(sc.hasNextLine()) {
				String linha = sc.nextLine();
				String[] split = linha.split(";");
				
				UF uf = UF.valueOf(split[1]);
				Integer cpf = Integer.parseInt(split[2]);
				long sequencial = Integer.parseInt(split[3]);
				int codigoDoMunicipio = Integer.parseInt(split[4]);
				int codCandidatoFederal = Integer.parseInt(split[5]);
				int codCandidatoRegional = Integer.parseInt(split[7]);

				System.out.println(codCandidatoFederal);
				System.out.println(ab.buscaBinaria(codCandidatoFederal));
				System.out.println(ab.buscaBinaria(codCandidatoRegional));
//				ab.emOrdem();
				Candidato candidatoFederal = ab.buscaBinaria(codCandidatoFederal).getConteudo();
				Candidato candidatoRegional= ab.buscaBinaria(codCandidatoRegional).getConteudo();
//				
				Eleitor eleitor = new Eleitor(uf,cpf,sequencial,codigoDoMunicipio,candidatoFederal,candidatoRegional);
//				
				ab.add(eleitor);
				
			}
		}
		return abb;
		

	}

}
