package pi.arvore;
import pi.model.Elemento;
import pi.node.Node;


public abstract class ArvoreBalanceada<T extends Elemento> extends ArvoreBinariaDeBusca<T>{
	
	public ArvoreBalanceada(T elemento) {
		super(elemento);
	}
	
	public ArvoreBalanceada() {
		super();
		
	}
	protected Node<T> RSE(Node<T> raiz) {
		
		Node<T> aux = raiz.getDireita();
		aux.setEsquerda(raiz);
		raiz.setDireita(null);
		return aux;
	}
	
	
	public Node<T> RSD(Node<T> raiz) {
		Node<T> aux = raiz.getEsquerda();
		aux.setDireita(raiz);
		raiz.setEsquerda(null);		
		return aux;

	}
	
	
	
	protected Node<T> RDD() {
		return null;
	}
	protected Node<T> RDE() {
		return null;
	}

}
