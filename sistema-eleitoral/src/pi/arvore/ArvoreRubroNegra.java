package pi.arvore;

import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra <T extends Elemento>extends ArvoreBalanceada<T> {
	

	private Node<T> pivo = new Node<T>(null, Cor.PRETO);
	
	public ArvoreRubroNegra(T elemento) {
		super(elemento);
	}
	
	
	protected boolean add(Node<T> raiz, Node<T> novo) {
		if(raiz.getElemento() == novo.getElemento())
			return false;
		else
			if(raiz.getElemento() < novo.getElemento()) {
				add(raiz.getDireita(),novo);
			}
		return false;
	}
	
	
	
}
