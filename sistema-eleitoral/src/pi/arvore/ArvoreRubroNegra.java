package pi.arvore;

import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra extends ArvoreBalanceada {
	

	private Node pivo = new Node(null, Cor.PRETO);
	
	public ArvoreRubroNegra(int elemento) {
		super(elemento);
	}
	
	@Override
	protected Node RSE() {
		return null;
	}

	@Override
	protected Node RSD() {
		return null;
	}

	@Override
	protected Node RDE() {
		return null;
	}

	@Override
	protected Node RDD() {
		return null;
	}

}
