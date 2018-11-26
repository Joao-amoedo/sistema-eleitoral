package pi.arvore.exception;

import pi.node.Node;

/**
 * Exceção lançada para sair da pilha de execução, encapsula um Node
 * @author 70485166151
 *
 */
public class FoundElementException extends RuntimeException{
	private Node raiz;
	
	
	
	public FoundElementException(Node raiz) {
		this.raiz = raiz;
	}



	public Node getNode() {return raiz;}
	
}
