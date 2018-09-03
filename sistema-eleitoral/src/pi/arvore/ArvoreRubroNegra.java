package pi.arvore;

import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra extends ArvoreBalanceada {
	

	private Node pivo = new Node(null, Cor.PRETO);
	
	public ArvoreRubroNegra(int elemento) {
		super(elemento);
	}
	
	@Override
	protected Node RSE(Node raiz) {
		return null;
	}

	@Override
	protected Node RSD(Node raiz) {
		return null;
	}

	@Override
	protected Node RDE(Node raiz) {
		return null;
	}

	@Override
	protected Node RDD(Node raiz) {
		return null;
	}

}
