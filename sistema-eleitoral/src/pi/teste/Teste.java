package pi.teste;

import pi.arvore.ArvoreBinaria;
import pi.node.Node;

public class Teste {
	public static void main(String[] args) {
		
		ArvoreBinaria ab = new ArvoreBinaria(100);

		ab.add(50);
		ab.add(40);
		ab.add(60);
		ab.add(55);
		ab.add(150);
		System.out.println("Ola jeff");
		ab.remove(100);
		
		ab.emOrdem();


		
		
	}
}
