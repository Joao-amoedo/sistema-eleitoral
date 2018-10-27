package pi.teste;

public class TesteRegex {
	public static void main(String[] args) {
		String cpf = "004.851.661-51";
		long replace = Long.parseLong(cpf.replace(".", "").replace("-", ""));
		System.out.println(replace);

	}

}
