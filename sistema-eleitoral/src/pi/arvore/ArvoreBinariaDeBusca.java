package pi.arvore;

import pi.model.Elemento;
import pi.node.Node;


public class ArvoreBinariaDeBusca <T extends Elemento> extends ArvoreBinaria<T> {

	public ArvoreBinariaDeBusca(T elemento) {
		super(elemento);
	}


	public ArvoreBinariaDeBusca() {
		super();
	}


	public Node<T> buscaBinaria(long elemento) {
		
		return buscaBinaria(elemento,this.raiz);
		
	}
	
	private Node<T> buscaBinaria(long elemento, Node<T> raiz){
		
		if(raiz.getElemento() > elemento)
			if(raiz.getEsquerda() != null)
				return buscaBinaria(elemento,raiz.getEsquerda());
			else
				return null;
		else if(raiz.getElemento() < elemento)
			if(raiz.getDireita() != null)
				return buscaBinaria(elemento,raiz.getDireita());
			else
				return null;
		else{
			return raiz;
		}
		
	}


	public Node<T> buscaProfundidade(long elemento) {
		return null;
	}


	public Node<T> buscaLargura(long elemento) {
		return null;
	}

}
