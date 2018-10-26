package pi.arvore;

import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra extends ArvoreBalanceada {
	

	private Node pivo = new Node(null, Cor.PRETO);
	
	public ArvoreRubroNegra(int elemento) {
		super(elemento);
	}
	
	
	protected boolean add(Node raiz, Node novo) {
		if(raiz.getElemento() == novo.getElemento())
			return false;
		else
			if(raiz.getElemento() < novo.getElemento()) {
				add(raiz.getDireita(),novo);
			}
		return false;
	}
	
	
	
}
