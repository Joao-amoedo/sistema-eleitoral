package pi.arvore;
import pi.model.Elemento;
import pi.node.Node;


public abstract class ArvoreBalanceada<T extends Elemento> extends ArvoreBinariaDeBusca<T>{
	
	public ArvoreBalanceada(T elemento) {
		super(elemento);
	}

	protected Node<T> RSE(Node<T> raiz) {
		return null;
	}
	protected Node<T> RSD() {
		return null;
	}
	protected Node<T> RDD() {
		return null;
	}
	protected Node<T> RDE() {
		return null;
	}

}
