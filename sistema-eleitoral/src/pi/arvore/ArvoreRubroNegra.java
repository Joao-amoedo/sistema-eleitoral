package pi.arvore;

import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra <T extends Elemento>extends ArvoreBalanceada<T> {
	

	private Node<T> pivo = new Node<T>(null, Cor.PRETO);
	
	public ArvoreRubroNegra(T elemento) {
		super(elemento);
	}
	
	
	
	
	
}
