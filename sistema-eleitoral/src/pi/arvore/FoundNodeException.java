package pi.arvore;

import pi.node.Node;

public class FoundNodeException extends RuntimeException{
	private Node raiz;
	
	
	
	public FoundNodeException(Node raiz) {
		this.raiz = raiz;
	}



	public Node getNode() {return raiz;}
	
}
