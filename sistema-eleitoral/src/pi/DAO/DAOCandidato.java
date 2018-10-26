package pi.DAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DAOCandidato {
	
	public void gravarArquivo(String texto) throws Exception {

		try (FileWriter fw = new FileWriter("candidato.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(texto);
		} catch (IOException e) {
			// exceção
		}
	}
}
