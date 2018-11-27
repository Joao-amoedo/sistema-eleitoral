package pi.main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.arvore.ArvoreBinariaDeBusca;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Partido;
import pi.model.TipoCandidato;

public class UrnaEletronica {
	private static ArvoreBinariaDeBusca<Eleitor> abEleitor = null;
	private static ArvoreBinariaDeBusca<Candidato> abCandidato = null;

	private static Scanner sc = new Scanner(System.in);

	private static void carregaDados() {
		abCandidato = DAOCandidato.lerArquivos();
		abEleitor = DAOEleitor.lerArquivo(abCandidato, 1);
	}

	public static void menu() {
		carregaDados();
		int opcao = 0;
		do {
			System.out.println("Digite a opção desejada:\n");
			System.out.println("1- Adicionar novo Candidato");
			System.out.println("2- Adicionar novo Eleitor");
			System.out.println("3- Mostrar Candidatos");
			System.out.println("4- Buscar por eleitor");
			System.out.println("5- Gerar massa de eleitores");
			System.out.println("0- Encerrar Eleição");
			opcao = Integer.parseInt(sc.nextLine());
			switch (opcao) {
			case 1:
				adicionaCandidato();
				break;
			case 3:
				mostrarCandidatos();
				break;
			}
		} while (opcao != 0);
		System.out.println("Encerrando Eleição");
	}
	//teste

	private static void mostrarCandidatos() {
		List<Candidato> list = abCandidato.toList();
		list.forEach(c -> {
			System.out.println(String.format("Nome: %s\tQuantidade de votos: %d\tPartido: %s\tTipo Candidato: %s",
					c.getNome(), c.getQtdVotos(), c.getPartido(), c.getTipoCandidato()));
		});
	}

	private static void adicionarEleitor() {
		Arrays.asList(abCandidato.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.FEDERAL);

		Arrays.asList(abCandidato.toList().stream().toArray()).stream()
				.filter(c -> ((Candidato) c).getTipoCandidato() == TipoCandidato.REGIONAL);
	}

	private static void adicionaCandidato() {
		TipoCandidato tipoCandidato = TipoCandidato.REGIONAL;

		System.out.println("Digite o nome do candidato: ");
		String nome = sc.nextLine();

		mostraPartidos();
		Partido partido = escolhePartido();

		Candidato cand = new Candidato(nome, partido, tipoCandidato);

		if (abCandidato.add(cand, true))
			System.out.println("Candidato inserido com sucesso");
		else
			System.out.println("\n\nCodigo do candidato " + cand.getElemento());

	}

	private static Partido escolhePartido() {
		Partido partido = null;
		do {
			partido = Partido.getPartido(Integer.parseInt(sc.nextLine()));
			if (partido == null)
				System.out.println("Partido inválido, tente novamente");
		} while (partido == null);
		return partido;
	}

	private static void mostraPartidos() {
		System.out.println("Digite o Partido desejado");
		Partido[] values = Partido.values();
		for (Partido part : values) {
			System.out.println(part);
		}
	}

}
