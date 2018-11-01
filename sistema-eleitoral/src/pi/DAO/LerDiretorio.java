package pi.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import pi.model.Candidato;
import pi.model.Partido;
import pi.model.TipoCandidato;

public class LerDiretorio {
	
	public static void gravarArquivo(String texto) throws Exception {

		try (FileWriter fw = new FileWriter("arquivo01.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}

	
	public static void lerDiretorio() {
		
		File diretorio = new File("C:\\Users\\Lucas Amdre\\Documents\\eclipse\\sistema-eleitoral\\workspace\\sistema-eleitoral\\condidatos");
		File[] arquivos = diretorio.listFiles();
		
//		for (File file : arquivos) {
			
			System.out.println(arquivos[1].getName());
			
			try(Scanner sc = new Scanner(new File(arquivos[0].getName()))){
				sc.nextLine();
				while(sc.hasNextLine()) {
					String linha = sc.nextLine();
					String[] split = linha.split(";");
					
					short codigoCandidato = Short.parseShort(split[1]);
					String nomeCandidato = split[2];
					Partido partido = Partido.getPartido(Integer.parseInt(split[3]));
					TipoCandidato tp = null;
					
					if(split[1].length() <= 4)
						tp = TipoCandidato.FEDERAL;
					else
						tp = TipoCandidato.REGIONAL;

					Candidato cad = new Candidato(nomeCandidato,partido,tp,codigoCandidato);
					System.out.println(cad);
					
				
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
//	}

}
}
