package pi.teste;

import java.util.Random;

import pi.arvore.ArvoreAVL;
import pi.arvore.ArvoreBinaria;
import pi.arvore.ArvoreRubroNegra;
import pi.node.Node;

public class Teste {
	public static void main(String[] args) {
		
		ArvoreBinaria ab = new ArvoreBinaria(100);
		Math.random();
		
		Random rad = new Random();
		int x = 0;
		
		int contador = 0;
		
		while(x < 10000) {
			if(ab.add(rad.nextInt(100000))) {
				x++;
			}else {
				contador++;
			}
		}
		System.out.println(contador);
		
		

	}
	
	
}

