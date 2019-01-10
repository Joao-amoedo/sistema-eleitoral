package pi.arvore.exception;

import pi.node.Node;

/**
 * Exce��o lan�ada para sair da pilha de execu��o, encapsula um Node
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
