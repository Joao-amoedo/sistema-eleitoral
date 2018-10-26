import pi.arvore.ArvoreBinaria;

public class TesteCPF {
	public static void main(String[] args) {
		
		ArvoreBinaria ab = new ArvoreBinaria(100);
		CPF geraCpf = new CPF();
		String  cpf = geraCpf.geraCPF();
		int contador = 0;
		double inicio = System.currentTimeMillis();
		int contadorErro = 0;
		while(contador < 2000000) {
			if(ab.add(Long.parseLong(geraCpf.geraCPF())))
				contador++;
			else
				contadorErro++;
		}
		double fim = System.currentTimeMillis();
		//ab.emOrdem();
		System.out.println("Erros: " + contadorErro);
		System.out.println(fim-inicio + " Milesegundos");
		
		
	
	
	}
}
